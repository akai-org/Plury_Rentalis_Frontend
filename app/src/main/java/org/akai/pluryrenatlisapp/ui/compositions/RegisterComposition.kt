package org.akai.pluryrenatlisapp.ui.compositions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
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
    val minorBackgroundColor = MaterialTheme.colorScheme.tertiary
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            shape = RoundedCornerShape(32.dp),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .size(160.dp)
                .drawBehind {
                    drawCircle(
                        color = minorBackgroundColor,
                        radius = center.y * 10f,
                        center = center.copy(y = -center.y * 9f)
                    )
            }
        ) {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = "Account Icon",
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        var email by remember { mutableStateOf("") }
        OutlinedEmailField(
            email = email,
            onEmailChange = { email = it.trim() },
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp),
            labelText = "Email"
        )

        var repeatedEmail by remember { mutableStateOf("") }
        OutlinedEmailField(
            email = repeatedEmail,
            onEmailChange = { repeatedEmail = it.trim() },
            modifier = Modifier
                .padding(bottom = 16.dp),
            labelText = "Email"
        )

        var nameAndSurname by remember { mutableStateOf("") }
        OutlinedNameAndSurnameFiled(
            nameAndSurname = nameAndSurname,
            onNameAndSurnameChang = { nameAndSurname = it.trim().replace(Regex("\\s+"), " ")},
            modifier = Modifier
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Zarejestruj się",
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