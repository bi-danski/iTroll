package org.me2you.itroll.cast

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.cast.CastPlayer
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.mediarouter.media.MediaRouteSelector
import androidx.mediarouter.media.MediaRouter
import com.google.android.gms.cast.CastDevice
import com.google.android.gms.cast.CastMediaControlIntent
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.cast.framework.CastSession
import com.google.android.gms.cast.framework.SessionManagerListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.me2you.itroll.root.state.CastDeviceKind
import org.me2you.itroll.root.state.CastDeviceUi

@OptIn(UnstableApi::class)
class AndroidCastController(private val context: Context) : CastController {

    private val mediaRouter = MediaRouter.getInstance(context)
    private val castContext = CastContext.getSharedInstance(context)
    private val castPlayer = CastPlayer.Builder(context).build()

    private val _availableDevices = MutableStateFlow<List<CastDeviceUi>>(emptyList())
    override val availableDevices: StateFlow<List<CastDeviceUi>> = _availableDevices.asStateFlow()

    private val _isConnected = MutableStateFlow(false)
    override val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    override val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _connectedDeviceName = MutableStateFlow<String?>(null)
    override val connectedDeviceName: StateFlow<String?> = _connectedDeviceName.asStateFlow()

    private val selector = MediaRouteSelector.Builder()
        .addControlCategory(CastMediaControlIntent.categoryForCast(CastConstants.DEFAULT_ID))
        .build()

    private val callback = object : MediaRouter.Callback() {
        override fun onRouteAdded(router: MediaRouter, route: MediaRouter.RouteInfo) {
            updateDevices()
        }

        override fun onRouteRemoved(router: MediaRouter, route: MediaRouter.RouteInfo) {
            updateDevices()
        }

        override fun onRouteChanged(router: MediaRouter, route: MediaRouter.RouteInfo) {
            updateDevices()
        }
    }

    private val sessionManagerListener = object : SessionManagerListener<CastSession> {
        override fun onSessionStarted(session: CastSession, sessionId: String) {
            _isConnected.value = true
            _connectedDeviceName.value = session.castDevice?.friendlyName
        }

        override fun onSessionEnded(session: CastSession, error: Int) {
            _isConnected.value = false
            _connectedDeviceName.value = null
        }

        override fun onSessionResumed(session: CastSession, wasSuspended: Boolean) {
            _isConnected.value = true
            _connectedDeviceName.value = session.castDevice?.friendlyName
        }

        override fun onSessionStarting(session: CastSession) {}
        override fun onSessionStartFailed(session: CastSession, error: Int) {}
        override fun onSessionEnding(session: CastSession) {}
        override fun onSessionResuming(session: CastSession, sessionId: String) {}
        override fun onSessionResumeFailed(session: CastSession, error: Int) {}
        override fun onSessionSuspended(session: CastSession, reason: Int) {}
    }

    init {
        castContext?.sessionManager?.addSessionManagerListener(sessionManagerListener, CastSession::class.java)
        castPlayer.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _isPlaying.value = isPlaying
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                _isPlaying.value = castPlayer.isPlaying
            }
        })
    }

    override fun startScanning() {
        mediaRouter.addCallback(selector, callback, MediaRouter.CALLBACK_FLAG_REQUEST_DISCOVERY)
        updateDevices()
    }

    override fun stopScanning() {
        mediaRouter.removeCallback(callback)
    }

    override fun connectToDevice(device: CastDeviceUi) {
        val route = mediaRouter.routes.find { it.id == device.id }
        route?.select()
    }

    override fun disconnect() {
        castContext.sessionManager.endCurrentSession(true)
    }

    override fun play() {
        castPlayer.play()
    }

    override fun pause() {
        castPlayer.pause()
    }

    override fun stop() {
        castPlayer.stop()
    }

    override fun castUrl(url: String, title: String) {
        val mediaItem = MediaItem.Builder()
            .setUri(url)
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setTitle(title)
                    .build()
            )
            .build()
        castPlayer.setMediaItem(mediaItem)
        castPlayer.prepare()
        castPlayer.play()
    }

    private fun updateDevices() {
        val routes = mediaRouter.routes
        val devices = routes.filter { it.matchesSelector(selector) && !it.isDefault }.map { route ->
            CastDeviceUi(
                id = route.id,
                name = route.name,
                kind = when (route.deviceType) {
                    MediaRouter.RouteInfo.DEVICE_TYPE_TV -> CastDeviceKind.TV
                    MediaRouter.RouteInfo.DEVICE_TYPE_SPEAKER -> CastDeviceKind.SPEAKER
                    MediaRouter.RouteInfo.DEVICE_TYPE_COMPUTER -> CastDeviceKind.LAPTOP
                    else -> CastDeviceKind.OTHER
                },
                description = route.description,
                status = when (route.connectionState) {
                    MediaRouter.RouteInfo.CONNECTION_STATE_CONNECTED -> "Connected"
                    MediaRouter.RouteInfo.CONNECTION_STATE_CONNECTING -> "Connecting"
                    else -> "Available"
                },
                iconUrl = route.iconUri?.toString(),
                ipAddress = CastDevice.getFromBundle(route.extras)?.ipAddress?.hostAddress
            )
        }
        _availableDevices.value = devices
    }
}
