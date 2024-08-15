package org.akai.pluryrenatlisapp.components

import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import org.akai.pluryrenatlisapp.ui.theme.getDefaultOutlinedTextFieldColors

@Composable
fun OutlinedEmailField(
    email: String,
    onEmailChange: (String) -> Unit
) {
    val isInvalidEmail = isProvidedInvalidEmail(email)
    Column {
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = {
                Text(
                    text = "Email",
                    modifier = Modifier.drawBehind {  }
                )
            },
            singleLine = true,
            colors = getDefaultOutlinedTextFieldColors(),
            isError = isInvalidEmail,
            shape = RoundedCornerShape(12.dp),
        )
        if (isInvalidEmail) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Info,
                    contentDescription = "Bad email provided warning icon",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .size(16.dp),

                )
                Text(
                    text = "Nieprawidłowy adres email",
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(start = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun isProvidedInvalidEmail(email: String) =
    email.isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()

@Preview
@Composable
fun OutlinedEmailFieldPreview() {
    var email by remember { mutableStateOf("") }
    OutlinedEmailField(
        email = email,
        onEmailChange = { email = it }
    )
}
@Preview
@Composable
fun OutlinedEmailFieldPreviewOnSurface() {
    var email by remember { mutableStateOf("") }
    Surface(color = MaterialTheme.colorScheme.surface) {
        OutlinedEmailField(
            email = email,
            onEmailChange = { email = it }
        )
    }
}