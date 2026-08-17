package org.me2you.itroll.cast

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.me2you.itroll.cast.state.CastDeviceUi

@Composable
expect fun CastOverlay(device: CastDeviceUi, modifier: Modifier = Modifier)
