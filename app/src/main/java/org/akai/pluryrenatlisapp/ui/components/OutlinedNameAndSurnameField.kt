package org.akai.pluryrenatlisapp.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OutlinedNameAndSurnameFiled (
    nameAndSurname: String,
    onNameAndSurnameChang: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextInput(
        text = nameAndSurname,
        onTextChange = onNameAndSurnameChang,
        isInputInvalid = getIsNameAndSurnameInvalid(nameAndSurname),
        labelText = "Imię i nazwisko",
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
        ),
        keyboardActions = keyboardActions,
        modifier = modifier
    )
}

@Composable
private fun getIsNameAndSurnameInvalid(nameAndSurname: String) = (nameAndSurname.isNotEmpty()
        && (nameAndSurname.trim().matches(Regex("^[A-Za-z]{2,}(\\s[A-Za-z]{2,})?(\\s[A-Za-z]{2,}(-[A-Za-z]{2,})*)+\$")).not()
        || nameAndSurname.trim().matches(Regex("^[A-Za-z]{2,}(-[A-Za-z]{2,})*(\\s[A-Za-z]{2,})+$")).not()))

@Preview
@Composable
fun OutlinedNameAndSurnameFiledPreview() {
    OutlinedNameAndSurnameFiled(nameAndSurname = "", onNameAndSurnameChang = {})
}