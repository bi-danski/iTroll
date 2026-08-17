package org.me2you.itroll.cast

import kotlinx.coroutines.flow.StateFlow
import org.me2you.itroll.root.state.CastDeviceUi

interface CastController {
    val availableDevices: StateFlow<List<CastDeviceUi>>
    val isConnected: StateFlow<Boolean>
    val isPlaying: StateFlow<Boolean>
    val connectedDeviceName: StateFlow<String?>

    fun startScanning()
    fun stopScanning()
    fun connectToDevice(device: CastDeviceUi)
    fun disconnect()
    
    // Media control
    fun play()
    fun pause()
    fun stop()
    fun castUrl(url: String, title: String)
}
