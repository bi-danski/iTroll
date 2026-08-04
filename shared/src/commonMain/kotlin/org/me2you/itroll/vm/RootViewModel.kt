package org.me2you.itroll.vm
//
//import androidx.lifecycle.ViewModel
//import android.content.Context
//import androidx.lifecycle.viewModelScope
//import androidx.media3.common.DeviceInfo
//import androidx.media3.common.MediaMetadata
//import androidx.media3.common.Player
//import androidx.media3.exoplayer.ExoPlayer
//import androidx.media3.cast.CastPlayer
//import androidx.media3.cast.RemoteCastPlayer
//import com.google.android.gms.cast.framework.CastContext
//import com.google.android.gms.cast.framework.CastState
//import com.google.android.gms.cast.framework.CastStateListener
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//
///**
// * Backs RootView. Wraps a single androidx.media3 [CastPlayer], which as of
// * media3 1.10 transparently swaps between local playback (ExoPlayer) and
// * remote playback (RemoteCastPlayer) depending on whether a Cast session is
// * active — connection state is read off [Player.Listener.onDeviceInfoChanged]
// * rather than the deprecated SessionAvailabilityListener.
// *
// * Device *discovery* count (how many cast targets are nearby, independent of
// * whether one is connected) comes from the Cast SDK's CastContext /
// * CastStateListener, not from media3 itself — media3 only knows about the
// * device it's currently connected to.
// */
//class RootViewModel(context: Context) : ViewModel() {
//
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
//        // CastState.NO_DEVICES_AVAILABLE / NOT_CONNECTED / CONNECTING / CONNECTED.
//        // Nearby-device *count* isn't exposed directly by CastContext; if you
//        // need an exact number, pair this with a MediaRouter callback
//        // (MediaRouter.Callback#onRouteAdded/onRouteRemoved) filtered to the
//        // Cast media route category. Here we surface a coarse signal instead.
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
//        // Route selection itself goes through the platform Cast dialog
//        // (androidx.mediarouter MediaRouteButton / CastButtonFactory) rather
//        // than media3 — media3 only owns playback once a session exists.
//        // Surface the intent here so the caller (RootScreen) can launch it.
//        viewModelScope.launch {
//            // e.g. mediaRouter.selectRoute(routeForDeviceId(device.id))
//        }
//    }
//
//    override fun onCleared() {
//        player.removeListener(playerListener)
//        castContext.removeCastStateListener(castStateListener)
//        player.release()
//        super.onCleared()
//    }
//}