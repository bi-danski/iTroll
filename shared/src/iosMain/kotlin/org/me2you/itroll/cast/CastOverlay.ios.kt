package org.me2you.itroll.cast

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.me2you.itroll.cast.state.CastDeviceKind
import org.me2you.itroll.cast.state.CastDeviceUi

@Composable
actual fun CastOverlay(device: CastDeviceUi, modifier: Modifier) {
    if (device.kind == CastDeviceKind.AIRPLAY) {
        AirPlayPicker(modifier = modifier)
    }
}
