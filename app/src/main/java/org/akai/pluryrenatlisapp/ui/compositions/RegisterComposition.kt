package org.akai.pluryrenatlisapp.ui.compositions

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import org.akai.pluryrenatlisapp.apiclient.Authorization
import org.akai.pluryrenatlisapp.ui.components.OutlinedEmailField
import org.akai.pluryrenatlisapp.ui.components.OutlinedNameAndSurnameFiled
import org.akai.pluryrenatlisapp.ui.theme.PluryRenatlisAppTheme

@Composable
fun RegisterComposition(
    authorizationHandling: (Authorization) -> Unit = {}
) {
    UtilityColumnWithLogo {
        var email by remember { mutableStateOf("") }
        OutlinedEmailField(
            email = email,
            onEmailChange = { email = it.trim() },
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp),
            labelText = "Email"
        )

        val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
        var repeatedEmail by remember { mutableStateOf("") }
        if (repeatedEmail.isNotEmpty() && email != repeatedEmail)
            OutlinedEmailField(
                email = repeatedEmail,
                onEmailChange = { repeatedEmail = it.trim() },
                modifier = Modifier
                    .padding(bottom = 16.dp),
                labelText = "Powtórz email",
                isInputInvalid = true, //todo: new field is unfocused
                invalidInputText = "Podane adresy email nie są takie same",
                interactionSource = interactionSource
            )
        else
            OutlinedEmailField(
                email = repeatedEmail,
                onEmailChange = { repeatedEmail = it.trim() },
                modifier = Modifier
                    .padding(bottom = 16.dp),
                labelText = "Powtórz email",
                interactionSource = interactionSource
            )

        var nameAndSurname by remember { mutableStateOf("") }
        OutlinedNameAndSurnameFiled(
            nameAndSurname = nameAndSurname,
            onNameAndSurnameChange = { nameAndSurname = it.replace(Regex("\\s+"), " ") },
            modifier = Modifier
                .padding(bottom = 16.dp),

            )

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(top = 16.dp),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.tertiary,
                disabledContentColor = MaterialTheme.colorScheme.onTertiary
            )
        ) {
            Text(
                text = "Zarejestruj",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
            )
        }
    }
}

@Preview(wallpaper = Wallpapers.NONE, showBackground = true)
@Composable
fun RegisterPreview() {
    PluryRenatlisAppTheme {
        RegisterComposition()
    }
}