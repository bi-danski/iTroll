package org.me2you.itroll.ui.view.rootview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.me2you.itroll.ui.navigation.Cast
import org.me2you.itroll.ui.navigation.Player
import org.me2you.itroll.ui.navigation.RootNavigator
import org.me2you.itroll.ui.view.rootview.components.ConnStatusBanner
import org.me2you.itroll.ui.view.rootview.components.RootCastCard
import org.me2you.itroll.ui.view.rootview.components.RootHeader
import org.me2you.itroll.ui.view.rootview.components.RootPlayerCard
import org.me2you.itroll.vm.RootViewModel

@Composable
fun RootView(rootNavigator: RootNavigator, rootViewModel: RootViewModel) {
    val rootUiState by rootViewModel.castUiState.collectAsStateWithLifecycle()

    Scaffold(modifier = Modifier) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 14.dp),
        ) {
            Spacer(Modifier.height(12.dp))
            RootHeader(onInfoClick = {  })
            Spacer(Modifier.height(10.dp))

            if (rootUiState.isConnected)
                ConnStatusBanner(deviceName = rootUiState.connectedDeviceName.orEmpty())

            Spacer(Modifier.height(18.dp))
            RootCastCard(
                availableDeviceCount = rootUiState.availableDeviceCount,
                recentDevices = rootUiState.recentDevices,
                onCardClick = { rootNavigator.navigateTo(Cast) },
                onQuickConnectClick = { rootViewModel.onQuickConnectClick(castDevice = it) },
            )

            Spacer(Modifier.height(18.dp))
            RootPlayerCard(
                castUiState = rootUiState,
                onCardClick = { rootNavigator.navigateTo(Player) },
                onPlayPauseClick = { rootViewModel.onPlayPauseClick() },
                onSkipNextClick = { rootViewModel.onSkipNextClick() },
                onSkipPreviousClick = { rootViewModel.onSkipPreviousClick() },
            )

            Spacer(Modifier.height(18.dp))
        }
    }
}









