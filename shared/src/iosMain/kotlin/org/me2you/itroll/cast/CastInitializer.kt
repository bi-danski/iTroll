package org.me2you.itroll.cast

import cocoapods.google_cast_sdk.GCKCastContext
import cocoapods.google_cast_sdk.GCKCastOptions
import cocoapods.google_cast_sdk.GCKDiscoveryCriteria
import kotlinx.cinterop.ExperimentalForeignApi

object CastInitializer {
    @OptIn(ExperimentalForeignApi::class)
    fun initialize() {
        val discoveryCriteria = GCKDiscoveryCriteria(applicationID = CastConstants.DEFAULT_ID)
        val options = GCKCastOptions(discoveryCriteria = discoveryCriteria)
        GCKCastContext.setSharedInstanceWithOptions(options)
    }
}
