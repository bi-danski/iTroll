package org.me2you.itroll

import android.media.session.MediaSession
import android.media.session.MediaSessionManager
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
//import androidx.media3.
import androidx.media3.cast.CastPlayer
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer

class MainActivity : ComponentActivity() {
    @OptIn(UnstableApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val exoPlayer = ExoPlayer.Builder(this@MainActivity).build()
        val castPlayer = CastPlayer.Builder(this@MainActivity).setLocalPlayer(exoPlayer).build()

//        mediaSession = MediaSession.//.Builder(this@MainActivity, castPlayer).build()
        setContent {
            App()
        }
    }

    override fun onStart() {
        super.onStart()

//        val mediaSession = androidx.media.MediaSessionManager.getSessionManager(th)
    }
}
