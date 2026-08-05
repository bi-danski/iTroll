package org.me2you.itroll.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Blue600 = Color(0xFF0B5FFF)
val Blue400 = Color(0xFF3D82FF)
val Blue50 = Color(0xFFE4EEFF)

val Purple600 = Color(0xFF7C3AED)
val Purple400 = Color(0xFFA876F2)
val Purple50 = Color(0xFFF0E7FE)

val Green600 = Color(0xFF12A150)
val Green50 = Color(0xFFE4F7EC)
val Amber500 = Color(0xFFF5A623)

val Neutral950 = Color(0xFF0E1116)
val Neutral50 = Color(0xFFF7F8FA)
val Neutral0 = Color(0xFFFFFFFF)
val Neutral500 = Color(0xFF6B7280)

val ITrollLightColorScheme: ColorScheme = lightColorScheme(
    primary = Blue600,
    onPrimary = Neutral0,
    primaryContainer = Blue50,
    onPrimaryContainer = Blue600,

    tertiary = Purple600,
    onTertiary = Neutral0,
    tertiaryContainer = Purple50,
    onTertiaryContainer = Purple600,

    secondaryContainer = Green50,
    onSecondaryContainer = Green600,

    error = Amber500,

    background = Neutral50,
    onBackground = Neutral950,
    surface = Neutral0,
    onSurface = Neutral950,
    surfaceVariant = Neutral50,
    onSurfaceVariant = Neutral500,
)

val ITrollDarkColorScheme: ColorScheme = darkColorScheme(
    primary = Blue400,
    onPrimary = Neutral950,
    primaryContainer = Blue600,
    onPrimaryContainer = Blue50,

    tertiary = Purple400,
    onTertiary = Neutral950,
    tertiaryContainer = Purple600,
    onTertiaryContainer = Purple50,

    secondaryContainer = Green50, //Green600,
    onSecondaryContainer = Green600, //Green50

    error = Amber500,

    background = Neutral950,
    onBackground = Neutral50,
    surface = Color(0xFF1A1D23),
    onSurface = Neutral50,
    surfaceVariant = Color(0xFF262A31),
    onSurfaceVariant = Neutral500,
)