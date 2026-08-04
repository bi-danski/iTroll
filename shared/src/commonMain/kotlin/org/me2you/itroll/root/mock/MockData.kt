package org.me2you.itroll.root.mock

import org.me2you.itroll.root.state.CastDeviceKind
import org.me2you.itroll.root.state.CastDeviceUi
import org.me2you.itroll.root.state.NowPlayingUi
import org.me2you.itroll.root.state.RootUiState

object MockRootData {
    val rootUiState = RootUiState(
        isConnected = false,
        connectedDeviceName = "192.168.126.7",
        availableDeviceCount = 3,
        recentDevices = listOf(
            CastDeviceUi("1", "192.168.126.237", CastDeviceKind.TV),
            CastDeviceUi("2", "192.168.126.192", CastDeviceKind.SPEAKER),
            CastDeviceUi("3", "192.168.126.7", CastDeviceKind.LAPTOP),
        ),
        nowPlaying = NowPlayingUi(
            title = "Midnight echoes",
            subtitle = "Paused · 02:14 / 03:40",
            isPlaying = false,
        ),
    )
}