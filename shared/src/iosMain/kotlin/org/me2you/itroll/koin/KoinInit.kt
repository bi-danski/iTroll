package org.me2you.itroll.koin

import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.koinConfiguration

fun koinInit() {
    startKoin(
        koinConfiguration {
            printLogger(Level.ERROR)
            modules(KoinModule.koinModules)
            createEagerInstances()
        }
    )
}