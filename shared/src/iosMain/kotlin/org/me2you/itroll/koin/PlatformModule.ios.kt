package org.me2you.itroll.koin

import org.koin.dsl.module
import org.me2you.itroll.cast.CastController
import org.me2you.itroll.cast.iOSCastController

actual val platformModule = module {
    single<CastController> { iOSCastController() }
}
