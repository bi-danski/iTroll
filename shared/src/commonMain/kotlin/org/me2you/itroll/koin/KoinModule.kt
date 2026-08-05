package org.me2you.itroll.koin

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.me2you.itroll.ui.navigation.RootNavigator
import org.me2you.itroll.vm.RootViewModel

object KoinModule {
    val koinModules = module {
        single(createdAtStart = false) { RootNavigator() }

        viewModelOf(::RootViewModel)
    }
}