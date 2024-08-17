package org.akai.pluryrenatlisapp.ui.components

import android.util.Patterns
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import org.akai.pluryrenatlisapp.ui.theme.PluryRenatlisAppTheme

@Composable
fun OutlinedEmailField (
    email: String,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    labelText: String = "Email",
    isInputInvalid: Boolean = isProvidedInvalidEmail(email),
    invalidInputText: String = "Nieprawidłowy email",
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    OutlinedTextInput(
        text = email,
        onTextChange = onEmailChange,
        isInputInvalid = isInputInvalid,
        labelText = labelText,
        invalidInputText = invalidInputText,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            imeAction = ImeAction.Next
        ),
        keyboardActions = keyboardActions,
        modifier = modifier,
        interactionSource = interactionSource
    )
}

@Composable
private fun isProvidedInvalidEmail(email: String) =
    email.isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()

@Preview
@Composable
fun OutlinedEmailFieldPreview() {
    var email by remember { mutableStateOf("") }
    PluryRenatlisAppTheme {
        OutlinedEmailField(
            email = email,
            onEmailChange = { email = it },
            labelText = "Email"
        )
    }
}
@Preview
@Composable
fun OutlinedEmailFieldPreviewOnSurface() {
    var email by remember { mutableStateOf("") }
    PluryRenatlisAppTheme {
        Surface {
            OutlinedEmailField(
                email = email,
                onEmailChange = { email = it },
                labelText = "Email"
            )
        }
    }
}