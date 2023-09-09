package presentation.player

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import presentation.home.cursorForItemClick
import uk.co.caprica.vlcj.player.base.MediaPlayer


data class PlayState(
    val isPlaying: Boolean = false,
    val isBuffering: Boolean = true,
    val length: Long = -1L,
    val currentTime: Long = 0L,
    val showControls: Boolean = true
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VideoPlayerComponent(
    modifier: Modifier = Modifier,
    url: String,
    subUrl: String? = null,
    onBack: () -> Unit = {}
){
    val scope = rememberCoroutineScope()
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var state by remember { mutableStateOf(PlayState()) }
    var currentSub by remember { mutableStateOf("") }

    Box(
        modifier = modifier.fillMaxSize()
    ){
        VideoPlayer(
            url = url,
            subUrl = subUrl,
            modifier = Modifier
                .fillMaxSize()
                .onPointerEvent(PointerEventType.Press) {
                    state = state.copy(showControls = true)
                    scope.launch {
                        delay(3000)
                        state = state.copy(showControls = false)
                    }
                },
            onPlayerReady = {
                mediaPlayer = it
                state = state.copy(
                    length = it.status().length(),
                    isBuffering = false,
                    isPlaying = true,
                    showControls = false
                )
            },
            onTimeChanged = {
                state = state.copy(currentTime = it, isBuffering = false)
            },
            onPlayerFinish = {
                state = state.copy(isPlaying = false, isBuffering = false, showControls = true)
            },
            onBuffer = {
                state = state.copy(isBuffering = true)
            },
            onSubReady = {
                if (currentSub != it)
                    currentSub = it
            }
        )
        IconButton(
            onClick = onBack,
            modifier = Modifier.size(50.dp).align(Alignment.TopStart).cursorForItemClick()
        ){
            Icon(Icons.Filled.ArrowBack, contentDescription = "back_videoScreen", tint = Color.White)
        }
        Text(
            currentSub,
            color = Color.White,
            fontSize = 28.sp,
            modifier = Modifier.align(Alignment.BottomCenter).padding(40.dp)
        )
        if (state.showControls) {
            SliderAndTime(
                state = state,
                onValueChanged = {
                    mediaPlayer?.controls()?.setTime((it * state.length).toLong())
                }
            )
            Controls(
                state = state,
                modifier = Modifier.align(Alignment.Center),
                playPause = {
                    if (state.isPlaying) {
                        state = state.copy(isPlaying = false)
                        mediaPlayer?.controls()?.pause()
                    }else{
                        state = state.copy(isPlaying = true)
                        mediaPlayer?.controls()?.play()
                    }
                },
                jump = {
                    mediaPlayer?.controls()?.setTime(state.currentTime + 10000)
                },
                rewind = {
                    mediaPlayer?.controls()?.setTime(state.currentTime - 10000)
                }
            )
        }

    }
}

@Composable
fun BoxScope.SliderAndTime(
    state: PlayState,
    modifier: Modifier = Modifier,
    onValueChanged: (Float) -> Unit
){
    val minute = ((state.length - state.currentTime) / (60 * 1000)) % 60
    val seconds = ((state.length - state.currentTime) / 1000) % 60
    val hour = ((state.length - state.currentTime) / (1000*60*60)) % 24
    val formattedSeconds = if (seconds >= 10) seconds else "0$seconds"
    val formattedMinute = if (minute >= 10) minute else "0$minute"
    val weight = if (hour > 0) 0.7f else 0.4f
    val totalTime = when{
        state.isBuffering -> "00:00"
        hour > 0 -> "0$hour:$formattedMinute:$formattedSeconds"
        else -> "$formattedMinute:$formattedSeconds"
    }
    Row(
        modifier = modifier.align(Alignment.BottomCenter).padding(bottom = 40.dp, start = 10.dp, end = 10.dp)
    ) {
        Slider(
            value = state.currentTime / state.length.toFloat(),
            onValueChange = onValueChanged,
            modifier = Modifier.cursorForItemClick().fillMaxWidth().weight(6f),
            colors = SliderDefaults.colors(thumbColor = Color.Red, activeTrackColor = Color.Red)
        )
        Text(
            totalTime,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterVertically).weight(weight)
        )
    }
}

@Composable
private fun Controls(
    state: PlayState,
    modifier: Modifier = Modifier,
    playPause: () -> Unit,
    rewind: () -> Unit,
    jump: () -> Unit
){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(
            onClick = rewind,
            modifier = Modifier.cursorForItemClick()
        ) {
            Icon(
                painterResource("rewind.png"),
                contentDescription = "fast_backward",
                tint = Color.White,
                modifier = Modifier.size(50.dp)
            )
        }
        IconButton(
            onClick = playPause,
            modifier = Modifier.cursorForItemClick()
        ) {
            when {
                state.isBuffering -> {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp), color = Color.Red)
                }
                state.isPlaying  -> {
                    Icon(
                        painterResource("icons8-pause-64.png"),
                        contentDescription = "pause",
                        tint = Color.White,
                        modifier = Modifier.size(50.dp)
                    )
                }
                else -> {
                    Icon(
                        painterResource("icons8-play-64.png"),
                        contentDescription = "play",
                        tint = Color.White,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        }
        IconButton(
            onClick = jump,
            modifier = Modifier.cursorForItemClick()
        ) {
            Icon(
                painterResource("fast-forward.png"),
                contentDescription = "fast_forward",
                tint = Color.White,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}
