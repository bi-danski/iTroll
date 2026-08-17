package org.me2you.itroll.ui.view.castview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.me2you.itroll.root.state.CastDeviceKind
import org.me2you.itroll.root.state.CastDeviceUi
import org.me2you.itroll.cast.AirPlayPicker

@Composable
actual fun PlatformCastOverlay(device: CastDeviceUi, modifier: Modifier) {
    if (device.kind == CastDeviceKind.AIRPLAY) {
        AirPlayPicker(modifier = modifier)
    }
}
