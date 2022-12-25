// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.*
import data.remote.Movie
import di.initKoin
import presentation.home.AppViewModel
import presentation.home.DrawerPane
import presentation.player.VideoPlayerComponent
import presentation.ui.NiteFlixTheme

fun main() = application(exitProcessOnExit = false) {
    initKoin()
    val scope = rememberCoroutineScope()
    val viewModel = AppViewModel.getViewModel()

    Window(
        onCloseRequest = ::exitApplication,
        title = "NiteFlix",
        resizable = true,
        icon = painterResource("app_icon.png"),
        state = WindowState(WindowPlacement.Floating)
    ) {
        NiteFlixTheme {
            var movie by remember { mutableStateOf<Movie?>(null) }
            var openPlayer by remember { mutableStateOf(false) }

            when{
                openPlayer -> {
                    VideoPlayerComponent(
                        url = movie!!.streamingUrl,
                        subUrl = movie!!.subtitleUrl,
                        onBack = {
                            viewModel.openMovieDetails(movie!!)
                            openPlayer = false
                        }
                    )
                }
                else ->{
                    DrawerPane(scope, viewModel){
                        openPlayer = true
                        movie = it
                    }
                }
            }
        }
    }
}
