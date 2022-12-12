package presentation.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposeImageBitmap
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.ColorAlphaType
import org.jetbrains.skia.ImageInfo
import org.koin.core.time.TimeInMillis
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer
import uk.co.caprica.vlcj.player.embedded.videosurface.CallbackVideoSurface
import uk.co.caprica.vlcj.player.embedded.videosurface.VideoSurfaceAdapters
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.BufferFormat
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.BufferFormatCallback
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.format.RV32BufferFormat
import uk.co.caprica.vlcj.subs.handler.SpuHandler
import uk.co.caprica.vlcj.subs.parser.SrtParser
import java.io.InputStreamReader
import java.net.URL
import java.nio.ByteBuffer
import java.security.Timestamp
import java.util.*
import javax.swing.JComponent
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration


@Composable
fun VideoPlayer(
    url: String,
    subUrl: String? = null,
    modifier: Modifier = Modifier,
    onPlayerReady: (MediaPlayer) -> Unit = {},
    onPlayerFinish: () ->Unit = {},
    onTimeChanged: (Long) -> Unit = {},
    onBuffer: () -> Unit = {},
    onSubReady: (String) -> Unit = {}
) {
    val windowState = rememberWindowState().size
    NativeDiscovery().discover()
    var imageBitmap by remember { mutableStateOf(ImageBitmap(windowState.width.value.toInt(), windowState.height.value.toInt())) }
    Image(modifier = modifier.background(Color.Black), bitmap = imageBitmap, contentDescription = "Video")
    val mediaPlayerComponent = rememberMediaPlayerComponent()
    var subs by remember { mutableStateOf(emptyList<Subtitle>()) }

    subUrl?.let {
        LaunchedEffect(subUrl) {
            subs = parseSub(it)
        }
    }

    DisposableEffect(Unit) {
        var byteArray: ByteArray? = null
        var imageInfo: ImageInfo? = null
        val mediaPlayer = mediaPlayerComponent.mediaPlayer()
        val callbackVideoSurface = CallbackVideoSurface(
            object : BufferFormatCallback {

                override fun getBufferFormat(sourceWidth: Int, sourceHeight: Int): BufferFormat {
                    imageInfo = ImageInfo.makeN32(sourceWidth, sourceHeight, ColorAlphaType.OPAQUE)
                    return RV32BufferFormat(sourceWidth, sourceHeight)
                }

                override fun allocatedBuffers(buffers: Array<out ByteBuffer>) {
                    byteArray = ByteArray(buffers[0].limit())
                }
            },
            { _, nativeBuffers, _ ->
                imageInfo?.let {
                    val byteBuffer = nativeBuffers[0]
                    byteBuffer.get(byteArray)
                    byteBuffer.rewind()
                    imageBitmap = Bitmap().apply {
                        allocPixels(it)
                        installPixels(byteArray)
                    }.asComposeImageBitmap()
                }
            },
            true,
            VideoSurfaceAdapters.getVideoSurfaceAdapter(),
        )
        mediaPlayer.videoSurface().set(callbackVideoSurface)
        mediaPlayer.media().play(url)
        mediaPlayer.events().addMediaPlayerEventListener(object : MediaPlayerEventAdapter() {

            override fun mediaPlayerReady(mediaPlayer: MediaPlayer) {
                super.mediaPlayerReady(mediaPlayer)
                onPlayerReady(mediaPlayer)
            }

            override fun finished(mediaPlayer: MediaPlayer) {
                super.finished(mediaPlayer)
                onPlayerFinish()
            }

            override fun timeChanged(mediaPlayer: MediaPlayer, newTime: Long) {
                super.timeChanged(mediaPlayer, newTime)
                onTimeChanged(newTime)
                val current = calculateTime(newTime)
                subs.find { subtitle ->
                    val start = subtitle.start.toString()
                    val end = subtitle.end.toString()
                    current in start..end
                }?.let { subtitle ->
                    onSubReady(subtitle.text)
                }
            }

            override fun buffering(mediaPlayer: MediaPlayer?, newCache: Float) {
                super.buffering(mediaPlayer, newCache)
                onBuffer()
            }


            override fun error(mediaPlayer: MediaPlayer?) {
                super.error(mediaPlayer)
                println("Error occurred")
            }
        })
        onDispose {
            mediaPlayer.release()
        }
    }
}
private fun Any.mediaPlayer(): EmbeddedMediaPlayer {
    return when (this) {
        is CallbackMediaPlayerComponent -> mediaPlayer()
        is EmbeddedMediaPlayerComponent -> mediaPlayer()
        else -> throw IllegalArgumentException("You can only call mediaPlayer() on vlcj player component")
    }
}

@Composable
fun rememberMediaPlayerComponent(): JComponent {
    return remember {
        // see https://github.com/caprica/vlcj/issues/887#issuecomment-503288294 for why we're using CallbackMediaPlayerComponent for macOS.
        if (isMacOS()) {
            CallbackMediaPlayerComponent()
        } else {
            EmbeddedMediaPlayerComponent()
        }
    }
}
private fun Duration.parseDuration(): String{
    val currentTime = toString().replace("h","")
        .replace("m","").replace("s","")
    val time = currentTime.split(" ")
    return when (time.size) {
        3 -> {
            val hour = time[0]
            val minute = time[1]
            val seconds = time[2]
            val hourFormatted = "0$hour"
            val minuteFormatted = if (minute.toInt() >= 10) minute else "0$minute"
            val secondsFormatted = seconds.getSeconds()
            "$hourFormatted:$minuteFormatted:$secondsFormatted"
        }
        2 -> {
            val minute = time[0]
            val seconds = time[1]
            val hourFormatted = "00"
            val minuteFormatted = if (minute.toInt() >= 10) minute else "0$minute"
            val secondsFormatted = seconds.getSeconds()
            "$hourFormatted:$minuteFormatted:$secondsFormatted"
        }
        else -> {
            val seconds = time[0]
            val hourFormatted = "00"
            val minuteFormatted = "00"
            val secondsFormatted = seconds.getSeconds()
            "$hourFormatted:$minuteFormatted:$secondsFormatted"
        }
    }
}
private fun calculateTime(time: Long): String{
    val duration = time.toDuration(DurationUnit.MILLISECONDS)
    return duration.parseDuration()
}
private fun String.getSeconds(): String{
    return  when{
        length < 4 -> "00.$this"
        toDouble().toInt() < 10 -> "0$this"
        else -> this
    }
}
private suspend fun parseSub(url: String): List<Subtitle>{
    val subtitleList = mutableListOf<Subtitle>()
    val subs = withContext(Dispatchers.IO) { URL(url).openStream() }.reader()
    subs.useLines {
        var index: Long? = null
        var begin: TimeStamp? = null
        var end: TimeStamp? = null
        var text: String? = null

        for (line in it.map { line -> line.trim() }){
            if (line.isNotEmpty()) {
                when {
                    (index == null) -> index = line.toLongOrNull()
                    (begin == null) -> {
                        val timestamps = line.split(" --> ")

                        if (timestamps.size != 2) {
                            return@useLines
                        }
                        begin = parseTimestamp(timestamps[0].trim())
                        end = parseTimestamp(timestamps[1].trim())
                    }
                    (text == null) -> text = line
                    else -> text += "\n$line"
                }
            } else if (text != null) {
                subtitleList.add(Subtitle(index!!, begin!!, end!!, text))
                index = null
                begin = null
                end = null
                text = null
            }
        }
    }
    return subtitleList.toList()
}

private fun isMacOS(): Boolean {
    val os = System.getProperty("os.name", "generic").lowercase(Locale.ENGLISH)
    return os.indexOf("mac") >= 0 || os.indexOf("darwin") >= 0
}
private fun String.format(): String{
    return replace("(\r\n|\n)".toRegex(), "\\\\n")
}
fun parseTimestamp(timestamp: String): TimeStamp? {
    return try {
        val parts = timestamp.split(":",",")
        TimeStamp(parts[0], parts[1], parts[2], parts[3])
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

data class TimeStamp(
    val hour: String,
    val minute: String,
    val seconds: String,
    val millis: String
){
    override fun toString(): String {
        return "$hour:$minute:$seconds.$millis"
    }
}
data class Subtitle(
    val id: Long,
    val start: TimeStamp,
    val end: TimeStamp,
    val text: String
)