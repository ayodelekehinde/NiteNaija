@file:OptIn(ExperimentalSplitPaneApi::class)

package presentation.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.rememberWindowState
import cafe.adriel.voyager.core.screen.Screen
import data.remote.Movie
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.rememberSplitPaneState
import presentation.ui.BLACK

@OptIn(ExperimentalSplitPaneApi::class)
@Preview
@Composable
fun DrawerPane(
    onPlay: (Movie) -> Unit
) {
    val splitterState = rememberSplitPaneState(moveEnabled = false)
    val hSplitterState = rememberSplitPaneState()
    val windowState = rememberWindowState()
    val width = windowState.size.width / 3
    val homeViewModel = HomeViewModel.getViewModel()
    HorizontalSplitPane(
        modifier = Modifier.background(BLACK),
        splitPaneState = hSplitterState
    ) {
        first(width) {
            SidePane(width)
        }
        second(50.dp) {
            FullPane(splitterState, homeViewModel, onPlay = onPlay)
        }
    }
}
