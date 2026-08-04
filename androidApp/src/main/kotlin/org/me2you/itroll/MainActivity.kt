package org.me2you.itroll

//import android.media.session.MediaSession
//import android.media.session.MediaSessionManager
//import android.support.v4.media.session.MediaSessionCompat
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.media3.
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import org.me2you.itroll.ui.theme.iTrollTheme
import org.me2you.itroll.root.view.CastDeviceKind
import org.me2you.itroll.root.view.CastDeviceUi
import org.me2you.itroll.root.view.NowPlayingUi
import org.me2you.itroll.root.view.RootUiState
import org.me2you.itroll.root.view.RootView

class MainActivity : ComponentActivity() {
    @OptIn(UnstableApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            iTrollTheme {
                RootView(
                    uiState = RootUiState(
                        isConnected = true,
                        connectedDeviceName = "Living room TV",
                        availableDeviceCount = 3,
                        recentDevices = listOf(
                            CastDeviceUi("1", "Living room", CastDeviceKind.TV),
                            CastDeviceUi("2", "Kitchen hub", CastDeviceKind.SPEAKER),
                            CastDeviceUi("3", "Office PC", CastDeviceKind.LAPTOP),
                        ),
                        nowPlaying = NowPlayingUi(
                            title = "Midnight echoes",
                            subtitle = "Paused · 02:14 / 03:40",
                            isPlaying = false,
                        ),
                    ),
                    onCastCardClick = {},
                    onPlayerCardClick = {},
                    onQuickConnectClick = {},
                    onPlayPauseClick = {},
                    onSkipNextClick = {},
                    onSkipPreviousClick = {},
                    onProfileClick = {},
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }
}
