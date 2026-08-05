package org.me2you.itroll.vm

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner

@Composable
actual fun rootViewModelStoreOwner(): ViewModelStoreOwner {
    return LocalViewModelStoreOwner.current ?: error("No ViewModelStoreOwner found in composition")
}