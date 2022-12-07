package presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.skiko.Cursor

@Composable
fun SidePane(width: Dp, onClick: () -> Unit = {}){
    Column(Modifier.width(width).padding(16.dp)) {
        Text(
            "NITEFLIX",
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            fontSize = 30.sp,
            color = Color.Red,
            textAlign = TextAlign.Center
        )
        Text("MENU", color = Color.White, fontSize = 14.sp, modifier = Modifier.padding(top = 15.dp))
        Box(Modifier.size(height = 3.dp, width = 20.dp).clip(RoundedCornerShape(2.dp)).background(Color.White))

        Column(Modifier.padding(top = 30.dp), verticalArrangement = Arrangement.spacedBy(15.dp)) {
            panelItems(Icons.Default.Home, "Home") {}
            panelItems(Icons.Default.AccountCircle, "Hollywood") {}
            panelItems(Icons.Default.AccountCircle, "Tv Series") {}
            panelItems(Icons.Default.AccountCircle, "Nollywood") {}
            panelItems(Icons.Default.AccountCircle, "Yollywood") {}
        }
    }
}

@Composable
fun panelItems(icon: ImageVector, text: String, onClick: () -> Unit) {
    val contentDescription = "panel_${text.lowercase()}"
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.cursorForItemClick()) {
        Icon(
            icon,
            contentDescription,
            tint = Color.White,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
    }
}

fun Modifier.cursorForItemClick(): Modifier =
    pointerHoverIcon(PointerIcon(Cursor(Cursor.HAND_CURSOR)))