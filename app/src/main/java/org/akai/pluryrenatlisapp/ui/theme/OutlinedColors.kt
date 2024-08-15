package org.akai.pluryrenatlisapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun getDefaultOutlinedTextFieldColors(): TextFieldColors {
    val surfaceColor = MaterialTheme.colorScheme.surface
    val darkerSurfaceColor = Color(
        red = surfaceColor.red - 0.05f,
        green = surfaceColor.green - 0.05f,
        blue = surfaceColor.blue - 0.05f,
        alpha = 1f
    )
    val borderColor = Color (
        red = surfaceColor.red * 0.1f,
        green = surfaceColor.green * 0.1f,
        blue = surfaceColor.blue * 0.1f,
        alpha = 1f
    )

    return OutlinedTextFieldDefaults.colors(
        cursorColor = MaterialTheme.colorScheme.onSurface,
        focusedContainerColor = darkerSurfaceColor,
        unfocusedContainerColor = darkerSurfaceColor,
        focusedLabelColor = borderColor,
        unfocusedLabelColor = borderColor,
        unfocusedBorderColor = borderColor,
        focusedBorderColor = borderColor,
        errorLabelColor = MaterialTheme.colorScheme.onSurface
    )
}