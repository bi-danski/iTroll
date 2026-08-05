package org.me2you.itroll.koin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.logger.Level
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.koinConfiguration

@OptIn(KoinExperimentalAPI::class)
class  KoinApp: Application(), KoinStartup {
    override fun onKoinStartup(): KoinConfiguration = koinConfiguration {
        androidContext(this@KoinApp)
        androidLogger(Level.ERROR)
        modules(KoinModule.koinModules)
        createEagerInstances()
    }
}