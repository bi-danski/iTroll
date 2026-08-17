package org.me2you.itroll.cast

import cocoapods.google_cast_sdk.GCKCastContext
import cocoapods.google_cast_sdk.GCKDevice
import cocoapods.google_cast_sdk.GCKDeviceCapabilityAudioOut
import cocoapods.google_cast_sdk.GCKDeviceCapabilityVideoOut
import cocoapods.google_cast_sdk.GCKDiscoveryManagerListenerProtocol
import cocoapods.google_cast_sdk.GCKImage
import cocoapods.google_cast_sdk.GCKMediaInformation
import cocoapods.google_cast_sdk.GCKMediaMetadata
import cocoapods.google_cast_sdk.GCKMediaMetadataTypeGeneric
import cocoapods.google_cast_sdk.GCKMediaStreamTypeBuffered
import cocoapods.google_cast_sdk.GCKSession
import cocoapods.google_cast_sdk.GCKSessionManager
import cocoapods.google_cast_sdk.GCKSessionManagerListenerProtocol
import cocoapods.google_cast_sdk.kGCKMetadataKeyTitle
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCSignatureOverride
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.me2you.itroll.cast.mock.MockRootData
import org.me2you.itroll.cast.state.CastDeviceKind
import org.me2you.itroll.cast.state.CastDeviceUi
import platform.Foundation.NSError
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
class IosCastController : CastController {

    private val castContext: GCKCastContext? by lazy { 
        try { GCKCastContext.sharedInstance() } catch (_: Exception) { null }
    }
    
    private val _availableDevices = MutableStateFlow<List<CastDeviceUi>>(emptyList())
    override val availableDevices: StateFlow<List<CastDeviceUi>> = _availableDevices.asStateFlow()

    private val _isConnected = MutableStateFlow(false)
    override val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    override val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _connectedDeviceName = MutableStateFlow<String?>(null)
    override val connectedDeviceName: StateFlow<String?> = _connectedDeviceName.asStateFlow()

    private val discoveryManagerListener = object : NSObject(), GCKDiscoveryManagerListenerProtocol {
        override fun didUpdateDeviceList() {
            updateDevices()
        }
    }

    private val sessionManagerListener = object : NSObject(), GCKSessionManagerListenerProtocol {
        @ObjCSignatureOverride
        override fun sessionManager(sessionManager: GCKSessionManager, didStartSession: GCKSession) {
            _isConnected.value = true
            _connectedDeviceName.value = didStartSession.device.friendlyName
        }

        @ObjCSignatureOverride
        override fun sessionManager(sessionManager: GCKSessionManager, didResumeSession: GCKSession) {
            _isConnected.value = true
            _connectedDeviceName.value = didResumeSession.device.friendlyName
        }

        @ObjCSignatureOverride
        override fun sessionManager(sessionManager: GCKSessionManager, didEndSession: GCKSession, withError: NSError?) {
            _isConnected.value = false
            _connectedDeviceName.value = null
        }
    }

    override fun startScanning() {
        val discoveryManager = castContext?.discoveryManager
        discoveryManager?.addListener(discoveryManagerListener)
        discoveryManager?.passiveScan = true
        discoveryManager?.startDiscovery()
        
        castContext?.sessionManager?.addListener(sessionManagerListener)
        updateDevices()
    }

    override fun stopScanning() {
        castContext?.discoveryManager?.removeListener(discoveryManagerListener)
        castContext?.sessionManager?.removeListener(sessionManagerListener)
        castContext?.discoveryManager?.stopDiscovery()
    }

    override fun connectToDevice(device: CastDeviceUi) {
        if (device.kind == CastDeviceKind.AIRPLAY) {
            // AirPlay
        } else {
            val discoveryManager = castContext?.discoveryManager
            val count = discoveryManager?.deviceCount ?: 0uL
            var targetDevice: GCKDevice? = null

            for (i in 0uL until count) {
                val d = discoveryManager?.deviceAtIndex(i)
                if (d?.deviceID == device.id) {
                    targetDevice = d
                    break
                }
            }

            if (targetDevice != null) {
                castContext?.sessionManager?.startSessionWithDevice(targetDevice)
            }
        }
    }

    override fun disconnect() {
        castContext?.sessionManager?.endSession()
    }

    override fun play() {
        castContext?.sessionManager?.currentCastSession?.remoteMediaClient?.play()
    }

    override fun pause() {
        castContext?.sessionManager?.currentCastSession?.remoteMediaClient?.pause()
    }

    override fun stop() {
        castContext?.sessionManager?.currentCastSession?.remoteMediaClient?.stop()
    }

    override fun castUrl(url: String, title: String) {
        val metadata = GCKMediaMetadata(GCKMediaMetadataTypeGeneric)
        metadata.setString(title, kGCKMetadataKeyTitle)
        
        val mediaInformation = GCKMediaInformation(
            contentID = url,
            streamType = GCKMediaStreamTypeBuffered,
            contentType = "video/mp4",
            metadata = metadata,
            adBreaks = null,
            adBreakClips = null,
            streamDuration = 0.0,
            mediaTracks = null,
            textTrackStyle = null,
            customData = null
        )

        castContext?.sessionManager?.currentCastSession?.remoteMediaClient?.loadMedia(mediaInformation)
    }

    private fun updateDevices() {
        val devices = mutableListOf<CastDeviceUi>()
        devices.add(MockRootData.airplayDevice)

        val discoveryManager = castContext?.discoveryManager
        val count = discoveryManager?.deviceCount ?: 0uL
        for (i in 0uL until count) {
            val device = discoveryManager?.deviceAtIndex(i)
            if (device != null) {
                devices.add(
                    CastDeviceUi(
                        id = device.deviceID ?: i.toString(),
                        name = device.friendlyName ?: "Chromecast",
                        kind = when {
                            device.hasCapabilities(GCKDeviceCapabilityVideoOut) -> CastDeviceKind.TV
                            device.hasCapabilities(GCKDeviceCapabilityAudioOut) -> CastDeviceKind.SPEAKER
                            else -> CastDeviceKind.OTHER
                        },
                        modelName = device.modelName,
                        status = device.statusText,
                        ipAddress = device.ipAddress,
                        iconUrl = (device.icons?.firstOrNull() as? GCKImage)?.URL?.absoluteString
                    )
                )
            }
        }
        _availableDevices.value = devices
    }
}
