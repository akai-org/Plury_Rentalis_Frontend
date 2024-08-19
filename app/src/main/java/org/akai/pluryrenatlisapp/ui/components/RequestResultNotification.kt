package org.akai.pluryrenatlisapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.akai.pluryrenatlisapp.ui.theme.PluryRenatlisAppTheme

@Composable
fun RequestResultNotification(
    modifier: Modifier = Modifier,
    text: String = "Wybrane rzeczy zostały pomyślnie wypożyczone",
    backgroundColor: Color = MaterialTheme.colorScheme.tertiaryContainer
) {
    Card (
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Text(
            text = text,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = Modifier.padding(32.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun RequestResultNotificationPreview() {
    PluryRenatlisAppTheme {
        RequestResultNotification()
    }
}