package org.me2you.itroll.root.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Airplay
import androidx.compose.material.icons.filled.CastConnected
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material.icons.filled.Speaker
import androidx.compose.material.icons.filled.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import org.me2you.itroll.root.state.CastDeviceKind

fun CastDeviceKind.toIcon(): ImageVector {
    return when (this) {
        CastDeviceKind.TV -> Icons.Filled.Tv
        CastDeviceKind.SPEAKER -> Icons.Filled.Speaker
        CastDeviceKind.LAPTOP -> Icons.Filled.Laptop
        CastDeviceKind.AIRPLAY -> Icons.Filled.Airplay
        CastDeviceKind.OTHER -> Icons.Filled.CastConnected
    }
}