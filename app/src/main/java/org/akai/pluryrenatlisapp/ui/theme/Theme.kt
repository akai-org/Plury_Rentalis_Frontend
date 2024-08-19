package org.akai.pluryrenatlisapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = PenBlue,
    onPrimary = White,
    primaryContainer = Night,
    onPrimaryContainer = HunyadiYellow,
    secondary = Iris,
    onSecondary = White,
    tertiary = HunyadiYellow,
    onTertiary = Night,
    background = LightGrey,
    onBackground = DarkGrey,
    surface = White,
    onSurface = Night,
)

private val LightColorScheme = lightColorScheme(
    primary = PenBlue,
    onPrimary = HunyadiYellow,
    primaryContainer = Night,
    onPrimaryContainer = HunyadiYellow,
    secondary = Iris,
    onSecondary = White,
    secondaryContainer = LightGrey,
    onSecondaryContainer = PenBlue,
    tertiary = HunyadiYellow,
    onTertiary = Night,
    tertiaryContainer = HunyadiYellowBrighter,
    onTertiaryContainer = PenBlue,
    background = LightGrey,
    onBackground = DarkGrey,
    surface = White,
    onSurface = Night,


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun PluryRenatlisAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}