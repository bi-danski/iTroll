package org.me2you.itroll.koin

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.me2you.itroll.cast.AndroidCastController
import org.me2you.itroll.cast.CastController

actual val platformModule = module {
    single<CastController> { AndroidCastController(androidContext()) }
}
