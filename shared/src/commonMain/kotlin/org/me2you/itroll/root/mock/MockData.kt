package org.me2you.itroll.root.mock

import org.me2you.itroll.root.state.CastDeviceKind
import org.me2you.itroll.root.state.CastDeviceUi
import org.me2you.itroll.root.state.NowPlayingUi
import org.me2you.itroll.root.state.RootUiState
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

object MockRootData {
    val airplayDevice = CastDeviceUi("airplay", "AirPlay / Apple TV", CastDeviceKind.AIRPLAY)

    @OptIn(ExperimentalUuidApi::class)
    val mockDevices = listOf(
        CastDeviceUi(
            id = Uuid.generateV7().toHexString(),
            name = "Living Room TV",
            kind = CastDeviceKind.TV,
            description = "Chromecast Ultra",
            modelName = "Chromecast Ultra",
            status = "Ready to cast",
            ipAddress = "192.168.1.15"
        ),
        CastDeviceUi(
            id = Uuid.generateV7().toHexString(),
            name = "Kitchen Speaker",
            kind = CastDeviceKind.SPEAKER,
            description = "Google Home Mini",
            modelName = "Google Home Mini",
            status = "Connected",
            ipAddress = "192.168.1.18"
        ),
        CastDeviceUi(
            id = Uuid.generateV7().toHexString(),
            name = "Office Laptop",
            kind = CastDeviceKind.LAPTOP,
            description = "Chrome Browser",
            modelName = "Chrome",
            status = "Available",
            ipAddress = "192.168.1.20"
        ),
    )

    val rootUiState = RootUiState(
        isConnected = false,
        connectedDeviceName = "192.168.126.7",
        availableDeviceCount = 3,
        recentDevices = mockDevices.shuffled(),
        nowPlaying = NowPlayingUi(
            title = "Party Tonight - Demarco",
            subtitle = "Paused · 02:14 / 03:06",
            isPlaying = false,
        ),
    )
}
