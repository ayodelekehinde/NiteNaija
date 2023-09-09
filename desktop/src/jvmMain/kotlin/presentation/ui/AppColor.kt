package presentation.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val DARK = Color(0xFF1A1A1B)
val BLACK = Color(0xFF000000)
val DARK_L = Color(0xFF191A19)
val TRANSPARENT_BLACK = Color(0x8C000000)

val darkColors = darkColors(
    primary = Color.White,
    onPrimary = BLACK,
    background = BLACK,
    onBackground = Color.White
)

@Composable
fun NiteFlixTheme(content: @Composable () -> Unit){
    MaterialTheme(colors = darkColors) {
        content()
    }
}