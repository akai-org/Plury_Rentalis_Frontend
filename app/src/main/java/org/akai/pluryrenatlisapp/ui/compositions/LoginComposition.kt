package org.akai.pluryrenatlisapp.ui.compositions

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.akai.pluryrenatlisapp.apiclient.Authorization
import org.akai.pluryrenatlisapp.apiclient.AuthorizationViewModel
import org.akai.pluryrenatlisapp.apiclient.RegisterViewModel
import org.akai.pluryrenatlisapp.ui.components.LabeledCheckbox
import org.akai.pluryrenatlisapp.ui.components.OutlinedEmailField
import org.akai.pluryrenatlisapp.ui.theme.PluryRenatlisAppTheme

@Composable
fun LoginComposition(
    authorizationHandling: (Authorization) -> Unit = {}
) {
    UtilityColumnWithLogo {
        var email: String by remember { mutableStateOf("") }
        OutlinedEmailField(
            email = email,
            onEmailChange = { email = it }
        )

        var rememberMe by remember { mutableStateOf(false) }
        LabeledCheckbox(
            label = "Zapamietaj mnie",
            checked = rememberMe,
            onCheckedChange = { rememberMe = it }
        )

        val preferences = LocalContext.current.getSharedPreferences("authorization", MODE_PRIVATE)
        if (rememberMe)
            preferences.edit().putString("email", email).apply()
        else if (preferences.contains("email"))
            preferences.edit().remove("email").apply()

        val registerVM: AuthorizationViewModel = viewModel()
        val token by registerVM.token.observeAsState()
        if (token?.isNotEmpty() == true)
            authorizationHandling(Authorization(token!!))
        Button(
            onClick = {
                registerVM.authorize(email)
            },
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
                text = "Zaloguj",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
            )
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    PluryRenatlisAppTheme {
        LoginComposition()
    }
}