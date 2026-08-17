package org.me2you.itroll.cast.state

data class CastDeviceUi(
    val id: String,
    val name: String,
    val kind: CastDeviceKind,
    val description: String? = null,
    val modelName: String? = null,
    val status: String? = null,
    val iconUrl: String? = null,
    val ipAddress: String? = null,
)

data class NowPlayingUi(
    val title: String,
    val subtitle: String,
    val isPlaying: Boolean,
)

data class CastUiState(
    val isConnected: Boolean = false,
    val connectedDeviceName: String? = null,
    val availableDeviceCount: Int = 0,
    val recentDevices: List<CastDeviceUi> = emptyList(),
    val nowPlaying: NowPlayingUi? = null,
)