package org.me2you.itroll.vm

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelStoreOwner


@Composable
actual fun rootViewModelStoreOwner(): ViewModelStoreOwner {
    return LocalActivity.current as? ComponentActivity
        ?: throw IllegalStateException("Must be hosted from a ComponentActivity")
}