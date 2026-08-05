package org.me2you.itroll

import androidx.compose.ui.window.ComposeUIViewController
import org.me2you.itroll.root.mock.MockRootData
import org.me2you.itroll.ui.view.rootview.RootView
import org.me2you.itroll.ui.theme.iTrollTheme

fun MainViewController() = ComposeUIViewController {
    iTrollTheme {
        RootView(
            uiState = MockRootData.rootUiState,
            onCastCardClick = {},
            onPlayerCardClick = {},
            onQuickConnectClick = {},
            onPlayPauseClick = {},
            onSkipNextClick = {},
            onSkipPreviousClick = {},
            onProfileClick = {},
        )
    }
}
