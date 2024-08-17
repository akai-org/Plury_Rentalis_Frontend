package org.akai.pluryrenatlisapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable


@Composable
fun getDefaultOutlinedTextFieldColors(): TextFieldColors {
    return OutlinedTextFieldDefaults.colors(
        cursorColor = MaterialTheme.colorScheme.onSecondaryContainer,
        focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        focusedLabelColor = MaterialTheme.colorScheme.onSecondaryContainer,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSecondaryContainer,
        unfocusedBorderColor = MaterialTheme.colorScheme.onSecondaryContainer,
        focusedBorderColor = MaterialTheme.colorScheme.onSecondaryContainer,
        errorLabelColor = MaterialTheme.colorScheme.onSecondaryContainer,
        errorCursorColor = MaterialTheme.colorScheme.onSecondaryContainer,
        errorContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        errorBorderColor = MaterialTheme.colorScheme.onSecondaryContainer,
    )
}