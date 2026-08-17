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
import org.me2you.itroll.root.mock.MockRootData
import org.me2you.itroll.root.state.CastDeviceUi
import org.me2you.itroll.root.state.NowPlayingUi
import org.me2you.itroll.root.state.RootUiState

class RootViewModel(
    private val castController: CastController
) : ViewModel() {

    private val _rootUiState: MutableStateFlow<RootUiState> = MutableStateFlow(MockRootData.rootUiState)
    val rootUiState: StateFlow<RootUiState> = _rootUiState.asStateFlow()

    init {
        castController.startScanning()

        castController.availableDevices.onEach { devices ->
            _rootUiState.update { it.copy(recentDevices = devices, availableDeviceCount = devices.size) }
        }.launchIn(viewModelScope)

        castController.isConnected.onEach { isConnected ->
            _rootUiState.update { it.copy(isConnected = isConnected) }
        }.launchIn(viewModelScope)

        castController.connectedDeviceName.onEach { name ->
            _rootUiState.update { it.copy(connectedDeviceName = name) }
        }.launchIn(viewModelScope)

        castController.isPlaying.onEach { isPlaying ->
            _rootUiState.update { state ->
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
        _rootUiState.update { state ->
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
