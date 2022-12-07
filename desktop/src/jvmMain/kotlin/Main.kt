// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import androidx.compose.ui.window.*
import di.initKoin
import presentation.home.DrawerPane
import presentation.ui.NiteFlixTheme

fun main() = application(exitProcessOnExit = false) {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "NiteFlix",
        resizable = true,
        state = WindowState(WindowPlacement.Floating)
    ) {
        NiteFlixTheme {
            DrawerPane()
        }
    }
}
