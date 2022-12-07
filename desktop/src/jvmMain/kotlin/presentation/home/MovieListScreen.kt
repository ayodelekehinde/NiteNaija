package presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.remote.Movie
import presentation.components.AsyncImage
import presentation.ui.DARK_L

enum class DrawerType {
    HOME, TVSERIES, NOLLYWOOD, HOLLYWOOD, YOLLYWOOD
}

@Composable
fun MovieListScreen(
    drawerType: DrawerType,
    data: Map<String,List<Movie>>,
    onClick: (Movie) -> Unit
) {
    //val list = listOf("Popular", "Watched", "Nollywood", "Yollywood", "Tvseries", "Hollywood", "My list")
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .background(color = DARK_L).padding(bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        items(data.toList()) {
            MovieList(it.first, it.second, onClick = onClick)
        }
    }
}

@Composable
fun MovieList(
    title: String,
    data: List<Movie>,
    onClick: (Movie) -> Unit
) {
    Text(
        title,
        color = Color.White,
        fontSize = 14.sp,
        modifier = Modifier.padding(start = 10.dp)
    )
    LazyRow(modifier = Modifier.padding(top = 10.dp, start = 10.dp), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        items(data) {
            MovieItem(movie = it, onClick = onClick)
        }
    }
}

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie: Movie, onClick: (Movie) ->Unit
){
    Column(modifier = modifier.clickable { onClick(movie) }.cursorForItemClick()) {
        AsyncImage(
            movie.image,
            modifier = Modifier.size(height = 120.dp, width = 230.dp),
            contentDescription = "image_url",
            loading = {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(40.dp).align(Alignment.Center)
                )
            },
            clipSize = 5
        )
        Text(
            movie.name,
            color = Color.White,
            fontSize = 10.sp,
            modifier = Modifier.padding(top = 10.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}