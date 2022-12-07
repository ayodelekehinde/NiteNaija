package presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.dp
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.writeBytes

val cacheImagePath = System.getProperty("user.home")!! +
        File.separator + "Pictures/.niteflix" + File.separator
@Composable
fun AsyncImage(
    url: String,
    loading: @Composable BoxScope.() -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    colorFilter: ColorFilter? = null,
    clipSize: Int = 0
) {
    var loadingState by remember { mutableStateOf(true) }
    val imageCacheFolder = File(cacheImagePath,url.getImageFileNameFromUrl())
    val image: ImageBitmap? by produceState<ImageBitmap?>(null) {
        if (imageCacheFolder.exists()){
            loadingState = false
            value = loadImageFile(imageCacheFolder)
        }else{
            value = withContext(Dispatchers.IO) {
                try {
                    val path = File(cacheImagePath.plus(url.getImageFileNameFromUrl()))
                    loadImageFromUrlAndCache(url, path)
                    loadingState = false
                    loadImageFile(path)
                } catch (e: IOException) {
                    e.printStackTrace()
                    null
                }
            }
        }
    }
    Box(modifier.clip(RoundedCornerShape(clipSize.dp))) {
        if (image != null && !loadingState) {
            Image(
                image!!,
                contentDescription = contentDescription,
                contentScale = contentScale,
                modifier = modifier,
                colorFilter = colorFilter
            )
        } else {
            loading()
        }
    }
}

fun clearCache(){
    val directory = File(cacheImagePath)
    val files: Array<File>? = directory.listFiles()
    if (files != null) {
        for (file in files) {
            if (file.isDirectory)
                continue
            file.delete()
        }
    }
}
fun String.getImageFileNameFromUrl() = substringAfterLast("/")
fun loadImageFile(file: File): ImageBitmap =
    file.inputStream().buffered().use(::loadImageBitmap)
suspend fun loadImageFromUrlAndCache(url: String, file: File) =
    urlStream(url,file)
private suspend fun urlStream(url: String, path: File) = HttpClient(CIO).use {
    val bt = ByteArrayInputStream(it.get(url).body())
    if (!path.parentFile.exists())
        path.parentFile.mkdirs()
    Files.createFile(Path.of(path.toURI())).writeBytes(bt.readAllBytes())
}
