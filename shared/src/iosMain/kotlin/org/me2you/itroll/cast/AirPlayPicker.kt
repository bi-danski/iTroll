package org.me2you.itroll.cast

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVKit.AVRoutePickerView
import platform.UIKit.UIColor

@OptIn(ExperimentalForeignApi::class)
@Composable
fun AirPlayPicker(modifier: Modifier = Modifier) {
    UIKitView(
        factory = {
            AVRoutePickerView().apply {
                backgroundColor = UIColor.clearColor
            }
        },
        modifier = modifier,
        properties = UIKitInteropProperties(
            isNativeAccessibilityEnabled = false
        )
    )
}
