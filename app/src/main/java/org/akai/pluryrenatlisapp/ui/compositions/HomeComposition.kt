package org.akai.pluryrenatlisapp.ui.compositions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.akai.pluryrenatlisapp.data.User
import org.akai.pluryrenatlisapp.ui.theme.PluryRenatlisAppTheme

@Composable
fun HomeComposition(
    user: User,
    rentButtonOnClick: () -> Unit = {},
    returnButtonOnClick: () -> Unit = {},
    registerButtonOnClick: () -> Unit = {},
    myRegisterButtonOnClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        val minorBackgroundColor = MaterialTheme.colorScheme.tertiary
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = Modifier
                .drawBehind {
                    val height = size.height
                    drawCircle(
                        color = minorBackgroundColor,
                        radius = height * 10,
                        center = center.copy(y = -height * 9.5f)
                    )
                },
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Zalogowany użytkownik",
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp
                    )
                )

                Text(
                    text = user.nameAndSurname,
                    fontStyle = MaterialTheme.typography.labelLarge.fontStyle,
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    modifier = Modifier.padding(
                        bottom = 16.dp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = rentButtonOnClick,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .padding(
                    bottom = 16.dp
                )
                .fillMaxWidth(3 / 5f)
                .height(50.dp)
        ) {
            Text(
                text = "Wypożycz",
                fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }
        Button(
            onClick = returnButtonOnClick,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .padding(
                    bottom = 16.dp,
                )
                .fillMaxWidth(3 / 5f)
                .height(50.dp)
        ) {
            Text(
                text = "Zwróć",
                fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }
        Button(
            onClick = registerButtonOnClick,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .padding(
                    bottom = 16.dp,
                )
                .fillMaxWidth(3 / 5f)
                .height(50.dp)
        ) {
            Text(
                text = "Rejestr",
                fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }

        Button(
            onClick = myRegisterButtonOnClick,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth(3 / 5f)
                .height(50.dp)
        ) {
            Text(
                text = "Twój rejestr",
                fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }
    }
}

@Preview
@Composable
fun HomeCompositionPreview() {
    PluryRenatlisAppTheme {
        HomeComposition(User("Jan Kowlaksi", "Kowalski"))
    }
}
