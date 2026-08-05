package org.me2you.itroll.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.me2you.itroll.root.mock.MockRootData
import org.me2you.itroll.root.state.RootUiState

class RootViewModel() : ViewModel() {

    private val _rootUiState: MutableStateFlow<RootUiState> = MutableStateFlow(MockRootData.rootUiState)
    val rootUiState: StateFlow<RootUiState> = _rootUiState.asStateFlow()


//    private val castContext: CastContext = CastContext.getSharedInstance(context)
//
//    private val player: CastPlayer = CastPlayer.Builder(context)
//        .setLocalPlayer(ExoPlayer.Builder(context).build())
//        .setRemotePlayer(RemoteCastPlayer.Builder(context).build())
//        .build()
//
//    private val _uiState = MutableStateFlow(RootUiState())
//    val uiState: StateFlow<RootUiState> = _uiState
//
//    private val playerListener = object : Player.Listener {
//        override fun onDeviceInfoChanged(deviceInfo: DeviceInfo) {
//            val connected = deviceInfo.playbackType == DeviceInfo.PLAYBACK_TYPE_REMOTE
//            _uiState.update {
//                it.copy(
//                    isConnected = connected,
//                    connectedDeviceName = if (connected) deviceInfo.name else null,
//                )
//            }
//        }
//
//        override fun onIsPlayingChanged(isPlaying: Boolean) {
//            updateNowPlaying(isPlaying = isPlaying)
//        }
//
//        override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {
//            updateNowPlaying(
//                title = mediaMetadata.title?.toString(),
//                artist = mediaMetadata.artist?.toString(),
//            )
//        }
//    }
//
//    private val castStateListener = CastStateListener { state ->
//        val hasNearbyDevices = state != CastState.NO_DEVICES_AVAILABLE
//        _uiState.update { it.copy(availableDeviceCount = if (hasNearbyDevices) it.availableDeviceCount.coerceAtLeast(1) else 0) }
//    }
//
//    init {
//        player.addListener(playerListener)
//        castContext.addCastStateListener(castStateListener)
//    }
//
//    private fun updateNowPlaying(
//        isPlaying: Boolean? = null,
//        title: String? = null,
//        artist: String? = null,
//    ) {
//        _uiState.update { state ->
//            val current = state.nowPlaying
//            val resolvedTitle = title ?: current?.title ?: return@update state
//            state.copy(
//                nowPlaying = NowPlayingUi(
//                    title = resolvedTitle,
//                    subtitle = artist ?: current?.subtitle.orEmpty(),
//                    isPlaying = isPlaying ?: current?.isPlaying ?: false,
//                ),
//            )
//        }
//    }
//
//    fun onPlayPauseClicked() {
//        if (player.isPlaying) player.pause() else player.play()
//    }
//
//    fun onSkipNextClicked() = player.seekToNext()
//
//    fun onSkipPreviousClicked() = player.seekToPrevious()
//
//    fun onQuickConnectClicked(device: CastDeviceUi) {
//
//        viewModelScope.launch {
//            mediaRouter.selectRoute(routeForDeviceId(device.id))
//        }
//    }
//
//    override fun onCleared() {
//        player.removeListener(playerListener)
//        castContext.removeCastStateListener(castStateListener)
//        player.release()
//        super.onCleared()
//    }
}