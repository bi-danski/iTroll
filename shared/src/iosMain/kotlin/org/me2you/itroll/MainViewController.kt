package org.me2you.itroll

import androidx.compose.ui.window.ComposeUIViewController
import org.me2you.itroll.ui.navigation.RootNavigation
import org.me2you.itroll.ui.theme.iTrollTheme

fun MainViewController() = ComposeUIViewController {
    iTrollTheme {

        RootNavigation()
    }
}
