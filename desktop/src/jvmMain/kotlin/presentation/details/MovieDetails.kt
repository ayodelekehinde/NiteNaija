package presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.remote.Movie
import data.remote.MovieType
import data.remote.Series
import presentation.components.AsyncImage
import presentation.home.AppViewModel
import presentation.home.HomeState
import presentation.home.MovieItem
import presentation.home.cursorForItemClick
import presentation.ui.TRANSPARENT_BLACK


sealed class MovieAction {
    data class SeriesClick(val series: Series) : MovieAction()
    object OnBack : MovieAction()
    data class OnPlay(val movie: Movie) : MovieAction()
}

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    viewModel: AppViewModel,
    movie: Movie,
    onBack: () -> Unit,
    onPlay: (Movie) -> Unit
) {
    val state = viewModel.container.stateFlow.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchMovieDetails(movie)
    }
    when (movie.movieType) {
        MovieType.SERIES -> {
            MovieDetailsView(
                modifier = modifier,
                movie = movie,
                state = state,
                otherView = {
                    SeriesDetailsScreen(state = state) {
                        onPlay(it)
                    }
                }
            ) {
                when (it) {
                    is MovieAction.OnBack -> onBack()
                    is MovieAction.OnPlay -> onPlay(it.movie)
                    is MovieAction.SeriesClick -> TODO()
                }
            }
        }

        else -> {
            MovieDetailsView(modifier = modifier, movie = movie, state = state) {
                when (it) {
                    is MovieAction.OnBack -> onBack()
                    is MovieAction.OnPlay -> onPlay(it.movie)
                    is MovieAction.SeriesClick -> TODO()
                }
            }
        }
    }
}

@Composable
fun SeriesDetailsScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    action: (Movie) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var season by remember { mutableStateOf(state.series.first().seasons.second) }
    var seasonName by remember { mutableStateOf(state.series.first().seasons.first) }

    Column(modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth().padding(15.dp)) {
            Text(
                text = "Episodes ($seasonName)",
                fontSize = 23.sp,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W500,
                modifier = modifier.weight(2f).align(Alignment.CenterVertically)
            )
            Box(modifier = Modifier.weight(1f).wrapContentSize(Alignment.TopEnd).align(Alignment.CenterVertically)) {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "Localized description",
                        tint = Color.White
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    state.series.forEach {
                        DropdownMenuItem(
                            onClick = {
                                season = it.seasons.second
                                seasonName = it.seasons.first
                                expanded = false
                            }
                        ) {
                            Text(it.seasons.first)
                        }
                    }
                }
            }
        }
        LazyRow(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            items(season) {
                MovieItem(movie = it) { selectedMovie ->
                    action(selectedMovie)
                }
            }
        }
    }
}


@Composable
fun EpisodeItem(
    movie: Movie
) {
    Row(Modifier.fillMaxWidth().padding(10.dp)) {
        AsyncImage(
            movie.image,
            loading = {},
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.size(150.dp, 90.dp)
        )
        Text(
            movie.name,
            fontSize = 18.sp,
            color = Color.White,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.W500,
            modifier = Modifier.padding(15.dp).fillMaxWidth()
        )

    }
}

@Composable
fun MovieDetailsView(
    modifier: Modifier = Modifier,
    movie: Movie,
    state: HomeState,
    otherView: @Composable () -> Unit = {},
    movieAction: (MovieAction) -> Unit
) {
    Box(modifier.fillMaxSize().background(color = Color.Black)) {
        AsyncImage(
            movie.image,
            loading = {},
            contentDescription = "",
            colorFilter = ColorFilter.tint(TRANSPARENT_BLACK, BlendMode.SrcAtop),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        IconButton(
            onClick = { movieAction(MovieAction.OnBack) },
            modifier = Modifier.cursorForItemClick()
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "", tint = Color.White)
        }
        Column(
            modifier = Modifier.padding(start = 10.dp, bottom = 50.dp).align(Alignment.BottomStart)
        ) {
            Text(
                movie.name,
                fontSize = 28.sp,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(bottom = 25.dp)
            )
            if (state.isDetailsLoading) {
                CircularProgressIndicator(Modifier.size(45.dp))
            } else {
                Text(
                    movie.summary,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
            Row(modifier = Modifier.padding(top = 40.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(
                    onClick = {
                        movieAction(MovieAction.OnPlay(state.movie!!))
                    },
                    enabled = !state.isDetailsLoading,
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.padding(start = 15.dp).size(height = 40.dp, width = 120.dp)
                        .cursorForItemClick(),
                    elevation = ButtonDefaults.elevation(5.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "")
                    Text("Play", modifier = Modifier.padding(start = 2.dp))
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(height = 40.dp, width = 120.dp).cursorForItemClick()
                ) {
                    Icon(Icons.Default.Add, contentDescription = "", tint = Color.White)
                }
            }
            if (state.series.isNotEmpty()) {
                otherView()
            }
        }

    }
}
