package org.me2you.itroll.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import org.koin.compose.viewmodel.koinViewModel
import org.me2you.itroll.ui.view.rootview.RootView
import org.me2you.itroll.vm.RootViewModel
import org.me2you.itroll.vm.rootViewModelStoreOwner

@Composable
fun RootNavigation(rootNavigator: RootNavigator, startRoute: NavKey) {
    val rootBackStack = rememberNavBackStack(RootSaveStateConfiguration, startRoute)

    LaunchedEffect(rootBackStack) {
        rootNavigator.attachBackStack(rootBackStack)
    }

    val rootViewModel = koinViewModel<RootViewModel>(viewModelStoreOwner = rootViewModelStoreOwner())

    NavDisplay(
        backStack = rootBackStack,
        onBack = { rootNavigator.popBack() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        transitionSpec = {
            slideInHorizontally(initialOffsetX = { it }) togetherWith slideOutHorizontally(targetOffsetX = { -it })
        },
        popTransitionSpec = {
            slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(targetOffsetX = { it })
        },
        predictivePopTransitionSpec = {
            slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(targetOffsetX = { it })
        },
        entryProvider = entryProvider {
            entry<Cast> {

            }

            entry<Root> {
                RootView(
                    rootNavigator = rootNavigator,
                    rootViewModel = rootViewModel
                )
            }

            entry<Player> { }
        }
    )
}