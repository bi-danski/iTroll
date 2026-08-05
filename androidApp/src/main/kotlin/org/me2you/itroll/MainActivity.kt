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
import org.me2you.itroll.root.mock.MockRootData
import org.me2you.itroll.ui.view.rootview.RootView
import org.me2you.itroll.ui.theme.iTrollTheme

class MainActivity : ComponentActivity() {
    @OptIn(UnstableApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            iTrollTheme {
                RootView(
                    uiState = MockRootData.rootUiState,
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
