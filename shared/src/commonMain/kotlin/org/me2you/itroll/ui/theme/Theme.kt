package org.me2you.itroll.ui.theme

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.DelegatableNode

@Suppress( "ComposableNaming")
@Composable
fun iTrollTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ITrollDarkColorScheme,//if (isSystemInDarkTheme()) ITrollDarkColorScheme else ITrollLightColorScheme,
        typography = iTrollTypography
    ) {
        CompositionLocalProvider(LocalIndication provides TouchRipples) {
            content()
        }
    }
}


object TouchRipples : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return object : Modifier.Node() {}
    }
    override fun hashCode() = -1
    override fun equals(other: Any?) = other === this
}