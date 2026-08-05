package org.me2you.itroll

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.compose.koinInject
import org.me2you.itroll.ui.navigation.Root
import org.me2you.itroll.ui.navigation.RootNavigation
import org.me2you.itroll.ui.navigation.RootNavigator
import org.me2you.itroll.ui.theme.iTrollTheme

fun MainViewController() = ComposeUIViewController {
    iTrollTheme {
        val rootNavigator: RootNavigator = koinInject<RootNavigator>()

        RootNavigation(
            rootNavigator = rootNavigator,
            startRoute = Root
        )
    }
}
