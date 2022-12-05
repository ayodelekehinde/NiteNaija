
package presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.SplitPaneScope
import org.jetbrains.compose.splitpane.SplitPaneState
import org.jetbrains.compose.splitpane.VerticalSplitPane
import presentation.ui.BLACK
import presentation.ui.DARK_L

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
fun FullPane(
    splitterState: SplitPaneState
){
    var text by remember { mutableStateOf("") }
    VerticalSplitPane(splitPaneState = splitterState) {
        first(80.dp) {
            Top(text){ text = it}
        }
        second(20.dp) {
            MovieListScreen(DrawerType.HOME)
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