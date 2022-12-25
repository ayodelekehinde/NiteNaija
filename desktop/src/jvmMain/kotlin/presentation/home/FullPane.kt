
package presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.remote.Movie
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.SplitPaneState
import org.jetbrains.compose.splitpane.VerticalSplitPane
import presentation.details.MovieDetailsScreen
import presentation.ui.BLACK
import presentation.ui.DARK_L

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
fun FullPane(
    sidePaneOptions: SidePaneOptions,
    splitterState: SplitPaneState,
    viewModel: AppViewModel,
    onPlay: (Movie) -> Unit
){

    var text by remember { mutableStateOf("") }
    val state = viewModel.container.stateFlow.collectAsState().value
    var hideSearchBar by remember { mutableStateOf(false) }

    if (sidePaneOptions != SidePaneOptions.HOME){
        hideSearchBar = true
    }
    val minHeight = if (state.isMovieDetails || hideSearchBar) 0 else 80


    LaunchedEffect(sidePaneOptions){
        viewModel.getTitles(sidePaneOptions)
    }

    VerticalSplitPane(splitPaneState = splitterState) {
        first(minHeight.dp) {
            Top(text){ text = it}
        }
        second(20.dp) {
            when{
                state.loading -> {
                    Box(Modifier.fillMaxSize().background(DARK_L)) {
                        CircularProgressIndicator(Modifier.size(50.dp).align(Alignment.Center), color = Color.White)
                    }
                }
                !state.loading && state.titles != null && !state.isMovieDetails && !state.isMoviePlaying ->{
                    MovieListScreen(sidePaneOptions, state.titles){
                        viewModel.openMovieDetails(it)
                    }
                }
                state.isMovieDetails && state.movie != null -> {
                    MovieDetailsScreen(
                        viewModel = viewModel,
                        movie = state.movie,
                        onBack = {viewModel.back()},
                        onPlay = {
                            viewModel.playMovie(it)
                        }
                    )
                }
                state.isMoviePlaying ->{
                    onPlay(state.movie!!)
                }
            }

        }
    }
}
@Composable
private fun Top(text: String, onChange: (String) -> Unit = {}){
    Column(Modifier.background(DARK_L).fillMaxSize()) {
        SearchBar(text, onChange = onChange)
    }
}

@Composable
private fun SearchBar(
    text: String,
    modifier: Modifier = Modifier,
    onChange: (String) -> Unit = {}
) {
    OutlinedTextField(
        text,
        placeholder = {
           Text("Search...", fontSize = 10.sp)
        },
        textStyle = TextStyle(color = Color.White, fontSize = 10.sp),
        onValueChange = onChange,
        maxLines = 1,
        shape = RoundedCornerShape(5.dp),
        leadingIcon = {
            Icon(Icons.Default.Search, "", tint = Color.White, modifier = Modifier.size(15.dp))
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.White,
            placeholderColor = Color.LightGray,
            focusedBorderColor = Color.Transparent,
            backgroundColor = BLACK
        ),
        modifier = modifier.fillMaxWidth()
            .height(65.dp)
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
    )

}