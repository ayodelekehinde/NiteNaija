package presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.remote.Movie
import presentation.components.AsyncImage
import presentation.ui.DARK_L



@Composable
fun MovieListScreen(
    currentPage: String,
    homeTitles: Map<String, List<Movie>>,
    isLoadingMore: Boolean,
    sidePaneOptions: SidePaneOptions,
    data: List<Movie>,
    onLoadMore: (SidePaneOptions, Int) -> Unit,
    onClick: (Movie) -> Unit
) {
    //val list = listOf("Popular", "Watched", "Nollywood", "Yollywood", "Tvseries", "Hollywood", "My list")
    val lazyState = rememberLazyGridState()
    val isScrolledToEnd by remember(data) {
        derivedStateOf {
            val totalItems = data.size
            val lastVisibleItem = lazyState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleItem == totalItems //- threshold
        }
    }

    LaunchedEffect(isScrolledToEnd, data.size){
        if (isScrolledToEnd){
            val page = (data.size) / 18 + 1
            onLoadMore(sidePaneOptions, page)
        }

    }

    when(sidePaneOptions){
        SidePaneOptions.HOME -> {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
                    .background(color = DARK_L).padding(bottom = 10.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {

                items(homeTitles.toList()) {
                    MovieList(it.first, it.second, onClick = onClick)
                }
            }
        }
        else -> {
            LazyVerticalGrid(
                state = lazyState,
                columns = GridCells.Fixed(5),
                modifier = Modifier.fillMaxWidth().background(color = DARK_L).padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                item(span = { GridItemSpan(currentLineSpan = 5) }) {
                    Text(
                        currentPage.uppercase(),
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        modifier = Modifier.padding(15.dp)
                    )
                }
                items(data, key = { it.id }){
                    MovieItem(
                      movie = it,
                        modifier = Modifier.padding(5.dp),
                        onClick = onClick
                    )
                }
                if (isLoadingMore) {
                    item(span = { GridItemSpan(currentLineSpan = 5) }) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }
            }
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