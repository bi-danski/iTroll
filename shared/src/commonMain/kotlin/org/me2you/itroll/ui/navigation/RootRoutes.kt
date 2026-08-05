package org.me2you.itroll.ui.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Serializable data object Cast: NavKey
@Serializable data object Root: NavKey
@Serializable data object Player: NavKey



val RootSaveStateConfiguration = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Cast::class)
            subclass(Root::class)
            subclass(Player::class)
        }
    }
}