package presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
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
fun SidePane(width: Dp, selected: SidePaneOptions, onClick: (SidePaneOptions) -> Unit = {}){

    Column(Modifier.width(width).padding(16.dp)) {
        Text(
            "NITEFLIX",
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            fontSize = 30.sp,
            color = Color.Red,
            textAlign = TextAlign.Center
        )
        Text("Menu", color = Color.White, fontSize = 14.sp, modifier = Modifier.padding(top = 15.dp))
        Box(Modifier.size(height = 3.dp, width = 20.dp).clip(RoundedCornerShape(2.dp)).background(Color.White))

        Column(Modifier.padding(top = 30.dp), verticalArrangement = Arrangement.spacedBy(15.dp)) {

            panelItems(
                icon = Icons.Default.Home,
                text = "Home",
                contentColor = {
                    if (selected.isIt(SidePaneOptions.HOME)) Color.White else Color.DarkGray
                },
                onClick = {onClick(SidePaneOptions.HOME)}
            )
            panelItems(
                icon = Icons.Default.AccountCircle,
                text = "Hollywood",
                contentColor = {
                    if (selected.isIt(SidePaneOptions.HOLLYWOOD)) Color.White else Color.DarkGray
                },
                onClick = { onClick(SidePaneOptions.HOLLYWOOD) }
            )
            panelItems(
                icon = Icons.Default.AccountCircle,
                text = "Tv Series",
                contentColor = {
                    if (selected.isIt(SidePaneOptions.TVSERIES)) Color.White else Color.DarkGray
                },
                onClick = {onClick(SidePaneOptions.TVSERIES)}
            )
            panelItems(
                icon = Icons.Default.AccountCircle,
                text = "Nollywood",
                contentColor = {
                    if (selected.isIt(SidePaneOptions.NOLLYWOOD)) Color.White else Color.DarkGray
                },
                onClick = {onClick(SidePaneOptions.NOLLYWOOD)}
            )
            panelItems(
                icon = Icons.Default.AccountCircle,
                text = "Yollywood",
                contentColor = {
                    if (selected.isIt(SidePaneOptions.YOLLYWOOD)) Color.White else Color.DarkGray
                },
                onClick = {onClick(SidePaneOptions.YOLLYWOOD)}
            )
        }
    }
}

fun SidePaneOptions.isIt(sidePaneOptions: SidePaneOptions) = this == sidePaneOptions

@Composable
fun panelItems(
    icon: ImageVector,
    text: String,
    contentColor: () -> Color,
    onClick: () -> Unit
) {
    val contentDescription = "panel_${text.lowercase()}"
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.cursorForItemClick()
            .clickable { onClick() }
    ) {
        Icon(
            icon,
            contentDescription,
            tint = contentColor(),
            modifier = Modifier.size(16.dp)
        )
        Text(
            text,
            color = contentColor(),
            fontSize = 14.sp,
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
    }
}

fun Modifier.cursorForItemClick(): Modifier =
    pointerHoverIcon(PointerIcon(Cursor(Cursor.HAND_CURSOR)))

sealed interface SidePaneOptions{
    object HOME: SidePaneOptions
    object HOLLYWOOD: SidePaneOptions
    object NOLLYWOOD: SidePaneOptions
    object TVSERIES: SidePaneOptions
    object YOLLYWOOD: SidePaneOptions
}