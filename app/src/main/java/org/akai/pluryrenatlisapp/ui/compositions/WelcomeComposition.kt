package org.akai.pluryrenatlisapp.ui.compositions

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.akai.pluryrenatlisapp.ui.theme.PluryRenatlisAppTheme

@Composable
fun WelcomeComposition(
    loginOnClick: () -> Unit,
    registerOnClick: () -> Unit
) {
    UtilityColumnWithLogo {
        Button(
            onClick = loginOnClick,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .padding(
                    top = 16.dp
                )
                .fillMaxWidth(3 / 5f)
                .height(50.dp)
        ) {
            Text(
                text = "Zaloguj",
                fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }

        Button(
            onClick = registerOnClick,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .padding(
                    top = 16.dp
                )
                .fillMaxWidth(3 / 5f)
                .height(50.dp)
        ) {
            Text(
                text = "Zarejestruj",
                fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }
    }
}

@Preview
@Composable
fun WelcomeCompositionPreview() {
    PluryRenatlisAppTheme {
        WelcomeComposition(
            loginOnClick = {},
            registerOnClick = {}
        )
    }
}