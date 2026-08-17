package org.me2you.itroll.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import org.me2you.itroll.cast.CastController
import org.me2you.itroll.cast.mock.MockRootData
import org.me2you.itroll.cast.state.CastDeviceUi
import org.me2you.itroll.cast.state.NowPlayingUi
import org.me2you.itroll.cast.state.CastUiState

class RootViewModel(
    private val castController: CastController
) : ViewModel() {

    private val _castUiState: MutableStateFlow<CastUiState> = MutableStateFlow(MockRootData.castUiState)
    val castUiState: StateFlow<CastUiState> = _castUiState.asStateFlow()

    init {
        castController.startScanning()

        castController.availableDevices.onEach { devices ->
            _castUiState.update { it.copy(recentDevices = devices, availableDeviceCount = devices.size) }
        }.launchIn(viewModelScope)

        castController.isConnected.onEach { isConnected ->
            _castUiState.update { it.copy(isConnected = isConnected) }
        }.launchIn(viewModelScope)

        castController.connectedDeviceName.onEach { name ->
            _castUiState.update { it.copy(connectedDeviceName = name) }
        }.launchIn(viewModelScope)

        castController.isPlaying.onEach { isPlaying ->
            _castUiState.update { state ->
                state.copy(
                    nowPlaying = state.nowPlaying?.copy(isPlaying = isPlaying)
                )
            }
        }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        castController.stopScanning()
    }

    fun onCastCardClick() {}
    fun onPlayerCardClick() {}
    fun onQuickConnectClick(castDevice: CastDeviceUi) {
        castController.connectToDevice(castDevice)
    }
    fun onPlayPauseClick() {
        if (castController.isPlaying.value) {
            castController.pause()
        } else {
            castController.play()
        }
    }
    fun onSkipNextClick() {}
    fun onSkipPreviousClick() {}
    fun onConnectClick(castDevice: CastDeviceUi) {
        castController.connectToDevice(castDevice)
    }

    fun castSample() {
        castController.castUrl(
            url = "https://storage.googleapis.com/wvmedia/clear/h264/tears/tears.mpd",
            title = "Tears of Steel"
        )
    }

    fun updateNowPlaying(isPlaying: Boolean? = null, title: String? = null, artist: String? = null) {
        _castUiState.update { state ->
            val current = state.nowPlaying
            val resolvedTitle = title ?: current?.title ?: return@update state
            state.copy(
                nowPlaying = NowPlayingUi(
                    title = resolvedTitle,
                    subtitle = artist ?: current?.subtitle.orEmpty(),
                    isPlaying = isPlaying ?: current?.isPlaying ?: false
                )
            )
        }
    }
}
