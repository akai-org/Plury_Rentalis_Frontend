package org.akai.pluryrenatlisapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RentableVerticalList(
    rents: List<RentableHolder>,
    toCheck: Boolean = true
) {
    Column {
        rents.forEachIndexed { index, rent ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (toCheck)
                    LabeledCheckbox(
                        label = rent.rent.rentableName,
                        checked = rent.checked.value,
                        onCheckedChange = rent.onCheckedChange
                    )
                else
                    Text(
                        text = rent.rent.rentableName,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        maxLines = 1,
                        modifier = Modifier.padding(8.dp)
                    )

                Text(
                    text = if ((rent.rent.userName?.trim()
                            ?: "") != ""
                    ) rent.rent.userName!!.trim() else "dostępne",
                    modifier = Modifier.padding(end = 16.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            }
            if (index < rents.lastIndex)
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    thickness = 2.dp
                )
        }
    }
}