package presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.components.AsyncImage
import presentation.components.loadImageBitmapLocal
import presentation.ui.DARK_L

enum class DrawerType {
    HOME, TVSERIES, NOLLYWOOD, HOLLYWOOD, YOLLYWOOD
}

@Composable
fun MovieListScreen(drawerType: DrawerType) {
    val list = listOf("Popular", "Watched", "Nollywood", "Yollywood", "Tvseries", "Hollywood", "Download", "Going")
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .background(color = DARK_L),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(list) {
            MovieList(it, list)
        }
    }
}

@Composable
fun MovieList(
    title: String,
    data: List<String>
) {
    Text(title, color = Color.White, fontSize = 14.sp, modifier = Modifier.padding(start = 5.dp))
    LazyRow(modifier = Modifier.padding(top = 10.dp), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        items(data) {
            AsyncImage(
               "https://static.netnaija.com/i/qvg702moNze.jpg",
                modifier = Modifier.size(height = 90.dp, width = 190.dp),
                contentDescription = "image_url",
                loading = {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(40.dp).align(Alignment.Center))
                }
            )
        }
    }

}