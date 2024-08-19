package org.akai.pluryrenatlisapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.akai.pluryrenatlisapp.ui.theme.PluryRenatlisAppTheme

@Composable
fun RequestResultNotification() {
    Card {
        Text(
            text = "Wybrane rzeczy zostały pomyślnie wyporzyczone",
            minLines = 2,
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