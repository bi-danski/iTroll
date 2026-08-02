package org.me2you.itroll.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

class RootNavigator {
    private var backStack: NavBackStack<NavKey>? = null

    var currentRoute by mutableStateOf<NavKey>(Cast)
        private set

    fun attachBackStack(stack: NavBackStack<NavKey>) {
        this.backStack = stack
        val top = stack.lastOrNull() ?: Cast
        this.currentRoute = top
    }

    fun navigateTo(dest: NavKey, clearBackStack: Boolean = false) {
        backStack?.let { stack ->
            if (clearBackStack) stack.clear()
            stack.add(dest)
            currentRoute = dest
        }
    }

    fun popBack() {
        backStack?.let { stack ->
            stack.removeLastOrNull()
            val top = stack.lastOrNull() ?: Cast
            currentRoute = top
        }
    }
}