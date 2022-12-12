// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.*
import data.remote.Movie
import di.initKoin
import presentation.home.DrawerPane
import presentation.player.VideoPlayerComponent
import presentation.ui.NiteFlixTheme

fun main() = application(exitProcessOnExit = false) {
    initKoin()
    var movie by remember { mutableStateOf<Movie?>(null) }
    var openPlayer by remember { mutableStateOf(false) }
    Window(
        onCloseRequest = ::exitApplication,
        title = "NiteFlix",
        resizable = true,
        state = WindowState(WindowPlacement.Floating)
    ) {
        NiteFlixTheme {
            if (openPlayer){
                VideoPlayerComponent(
                    url = movie!!.streamingUrl,
                    subUrl = movie!!.subtitleUrl
                )
            }else {
                DrawerPane{
                    movie = it
                    openPlayer = true
                }
            }
        }
    }
}
