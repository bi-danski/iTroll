package org.me2you.itroll.ui.view.rootview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.me2you.itroll.root.mock.MockRootData
import org.me2you.itroll.root.state.CastDeviceUi
import org.me2you.itroll.root.state.RootUiState
import org.me2you.itroll.ui.theme.iTrollTheme
import org.me2you.itroll.ui.view.rootview.components.ConnStatusBanner
import org.me2you.itroll.ui.view.rootview.components.RootCastCard
import org.me2you.itroll.ui.view.rootview.components.RootHeader
import org.me2you.itroll.ui.view.rootview.components.RootPlayerCard

@Composable
fun RootView(
    uiState: RootUiState,
    onCastCardClick: () -> Unit,
    onPlayerCardClick: () -> Unit,
    onQuickConnectClick: (CastDeviceUi) -> Unit,
    onPlayPauseClick: () -> Unit,
    onSkipNextClick: () -> Unit,
    onSkipPreviousClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 18.dp),
        ) {
            Spacer(Modifier.height(12.dp))
            RootHeader(onProfileClick = onProfileClick)

            Spacer(Modifier.height(10.dp))

            if (uiState.isConnected)
                ConnStatusBanner(deviceName = uiState.connectedDeviceName.orEmpty())

            Spacer(Modifier.height(18.dp))
            RootCastCard(
                availableDeviceCount = uiState.availableDeviceCount,
                recentDevices = uiState.recentDevices,
                onCardClick = onCastCardClick,
                onQuickConnectClick = onQuickConnectClick,
            )

            Spacer(Modifier.height(18.dp))
            RootPlayerCard(
                rootUiState = uiState,
                onCardClick = onPlayerCardClick,
                onPlayPauseClick = onPlayPauseClick,
                onSkipNextClick = onSkipNextClick,
                onSkipPreviousClick = onSkipPreviousClick,
            )

            Spacer(Modifier.height(18.dp))
        }
    }
}

@Preview
@Composable
fun RootPreview(){
    iTrollTheme {
        RootView(
            MockRootData.rootUiState,
            {},
            {},
            {},
            {},
            {},
            {},
            {}
        )
    }
}










