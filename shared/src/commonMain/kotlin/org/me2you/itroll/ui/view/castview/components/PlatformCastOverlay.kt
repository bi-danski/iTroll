package org.me2you.itroll.ui.view.castview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.me2you.itroll.root.state.CastDeviceUi

@Composable
expect fun PlatformCastOverlay(device: CastDeviceUi, modifier: Modifier = Modifier)
