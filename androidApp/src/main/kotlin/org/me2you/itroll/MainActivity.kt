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
import org.koin.compose.koinInject
import org.me2you.itroll.ui.navigation.Root
import org.me2you.itroll.ui.navigation.RootNavigation
import org.me2you.itroll.ui.navigation.RootNavigator
import org.me2you.itroll.ui.theme.iTrollTheme

class MainActivity : ComponentActivity() {
    @OptIn(UnstableApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val rootNavigator = koinInject<RootNavigator>()

            iTrollTheme {
                RootNavigation(rootNavigator = rootNavigator, startRoute = Root)
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }
}
