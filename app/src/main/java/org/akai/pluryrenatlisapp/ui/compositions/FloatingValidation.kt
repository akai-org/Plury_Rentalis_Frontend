package org.akai.pluryrenatlisapp.ui.compositions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.akai.pluryrenatlisapp.data.Rent

@Composable
fun FloatingValidation(
    rents: List<Rent>,
    accept: suspend (List<Rent>) -> Unit,
    reject: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 100.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = MaterialTheme.shapes.large
    ) {

        val minorBackgroundColor = MaterialTheme.colorScheme.tertiary
        Column(
            modifier = Modifier
                .padding(16.dp)
                .drawBehind {
                    drawCircle(
                        color = minorBackgroundColor,
                        radius = 5000f,
                        center = center.copy(y = -4850f)
                    )
                }
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Wybrane wyporzyczenia",
                color = MaterialTheme.colorScheme.primary,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = MaterialTheme.typography.headlineLarge.fontWeight
            )

            Spacer(modifier = Modifier.height(32.dp))

            for ((index, rent) in rents.withIndex()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                )  {
                    Text(text = rent.rentableName)
                    Text(text = rent.userName ?: "dostępne")
                }
                if (index < rents.size - 1)
                    HorizontalDivider()
            }

            Spacer(modifier = Modifier.height(32.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Button(
                    onClick = reject,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    Text(text = "Anuluj")
                }
                val acceptCoroutineScope = rememberCoroutineScope()
                Button(
                    onClick =
                    {
                        acceptCoroutineScope.launch { accept(rents) }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Potwierdź")
                }
            }
        }
    }
}

@Preview
@Composable
fun FloatingValidationPreview() {
    FloatingValidation(
        rents = listOf(
            Rent(),
            Rent(),
            Rent(),
            Rent(),
            Rent(rentableType = "Auto"),
            Rent(rentableType = "Auto"),
            ),
        accept = {},
        reject = {}
    )
}