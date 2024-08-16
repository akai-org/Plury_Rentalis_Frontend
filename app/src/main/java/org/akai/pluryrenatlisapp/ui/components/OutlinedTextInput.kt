package org.akai.pluryrenatlisapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.akai.pluryrenatlisapp.ui.theme.getDefaultOutlinedTextFieldColors

@Composable
fun OutlinedTextInput(
    text: String,
    onTextChange: (String) -> Unit,
    isInputInvalid: Boolean,
    labelText: String,
    modifier: Modifier = Modifier,
    invalidInputText: String = "Invalid input",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
    ) {
    Column (
        modifier = modifier
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            label = {
                Text(
                    text = labelText,
                )
            },
            singleLine = true,
            colors = getDefaultOutlinedTextFieldColors(),
            isError = isInputInvalid,
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )
        if (isInputInvalid) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Info,
                    contentDescription = "Invalid input warning icon",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .size(16.dp),

                    )
                Text(
                    text = invalidInputText,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(start = 4.dp)
                )
            }
        }
    }
}