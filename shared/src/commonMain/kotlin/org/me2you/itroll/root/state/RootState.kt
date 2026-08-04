package org.me2you.itroll.root.state

data class CastDeviceUi(
    val id: String,
    val name: String,
    val kind: CastDeviceKind,
)

data class NowPlayingUi(
    val title: String,
    val subtitle: String,
    val isPlaying: Boolean,
)

data class RootUiState(
    val isConnected: Boolean = false,
    val connectedDeviceName: String? = null,
    val availableDeviceCount: Int = 0,
    val recentDevices: List<CastDeviceUi> = emptyList(),
    val nowPlaying: NowPlayingUi? = null,
)