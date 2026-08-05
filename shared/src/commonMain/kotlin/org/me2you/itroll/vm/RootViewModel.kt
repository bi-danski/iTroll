package org.me2you.itroll.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.me2you.itroll.root.mock.MockRootData
import org.me2you.itroll.root.state.CastDeviceUi
import org.me2you.itroll.root.state.NowPlayingUi
import org.me2you.itroll.root.state.RootUiState

class RootViewModel() : ViewModel() {

    private val _rootUiState: MutableStateFlow<RootUiState> = MutableStateFlow(MockRootData.rootUiState)
    val rootUiState: StateFlow<RootUiState> = _rootUiState.asStateFlow()

    fun onCastCardClick() {}
    fun onPlayerCardClick() {}
    fun onQuickConnectClick(castDevice: CastDeviceUi) { }
    fun onPlayPauseClick() {}
    fun onSkipNextClick() {}
    fun onSkipPreviousClick() {}
    fun onProfileClick() {}

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