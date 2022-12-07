package presentation.details

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.remote.Movie
import data.remote.MovieType
import presentation.components.AsyncImage
import presentation.home.HomeViewModel
import presentation.home.cursorForItemClick
import presentation.ui.TRANSPARENT_BLACK

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    viewModel: HomeViewModel,
    movie: Movie,
    onBack: () -> Unit
){
    val state = viewModel.container.stateFlow.collectAsState().value
    LaunchedEffect(Unit){
        viewModel.fetchMovieDetails(movie)
    }
    Box(modifier.fillMaxSize().background(color = Color.Magenta)){
        AsyncImage(
            movie.image,
            loading = {},
            contentDescription = "",
            colorFilter = ColorFilter.tint(TRANSPARENT_BLACK, BlendMode.SrcAtop),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        IconButton(
            onClick = onBack,
            modifier = Modifier.cursorForItemClick()
        ){
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
            if (state.isDetailsLoading){
                CircularProgressIndicator(Modifier.size(45.dp))
            }else {
                Text(
                    movie.summary,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
            Row(modifier = Modifier.padding(top = 40.dp),horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.size(height = 40.dp, width = 120.dp).cursorForItemClick(),
                    elevation = ButtonDefaults.elevation(5.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "")
                    Text("Play", modifier = Modifier.padding(start = 2.dp))
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(height = 40.dp, width = 120.dp).cursorForItemClick()
                ){
                    Icon(Icons.Default.Add, contentDescription = "", tint = Color.White)
                }
            }
        }
    }
}