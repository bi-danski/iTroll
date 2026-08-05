package org.me2you.itroll.root.mock

import org.me2you.itroll.root.state.CastDeviceKind
import org.me2you.itroll.root.state.CastDeviceUi
import org.me2you.itroll.root.state.NowPlayingUi
import org.me2you.itroll.root.state.RootUiState
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

object MockRootData {
    @OptIn(ExperimentalUuidApi::class)
    val rootUiState = RootUiState(
        isConnected = false,
        connectedDeviceName = "192.168.126.7",
        availableDeviceCount = 3,
        recentDevices = listOf(
            CastDeviceUi(Uuid.generateV7().toHexString(), "192.168.126.237", CastDeviceKind.TV),
            CastDeviceUi(Uuid.generateV7().toHexString(), "192.168.126.192", CastDeviceKind.SPEAKER),
            CastDeviceUi(Uuid.generateV7().toHexString(), "192.168.126.7", CastDeviceKind.LAPTOP),
        ).shuffled(),
        nowPlaying = NowPlayingUi(
            title = "Party Tonight - Demarco",
            subtitle = "Paused · 02:14 / 03:06",
            isPlaying = false,
        ),
    )
}

