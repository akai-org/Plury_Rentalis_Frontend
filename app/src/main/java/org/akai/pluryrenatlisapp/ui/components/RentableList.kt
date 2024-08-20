package org.akai.pluryrenatlisapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.akai.pluryrenatlisapp.data.Rentable
import org.akai.pluryrenatlisapp.data.RentableType
import org.akai.pluryrenatlisapp.ui.theme.PluryRenatlisAppTheme

@Composable
fun RentableList(
    title: String,
    rentables: List<RentableHolder>,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 16.dp),
            color = MaterialTheme.colorScheme.secondary
        )
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth(),
            border = BorderStroke(
                2.dp,
                MaterialTheme.colorScheme.secondary
            ),
        ) {
            LazyColumn {
                itemsIndexed(rentables) { index, rentable ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LabeledCheckbox(
                            label = rentable.rentable.name,
                            checked = rentable.checked.value,
                            onCheckedChange = rentable.onCheckedChange
                        )
                        Text(
                            text = rentable.rentable.renter ?: "dostępne",
                            modifier = Modifier.padding(end = 16.dp),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        )
                    }
                    if (index < rentables.lastIndex)
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            thickness = 2.dp
                        )
                }
            }
        }
    }
}

data class RentableHolder(
    var checked: MutableState<Boolean>,
    val rentable: Rentable,
    val onCheckedChange: (Boolean) -> Unit = { checked.value = it }
)

@Preview
@Composable
fun RentableListPreview() {
    PluryRenatlisAppTheme {
        RentableList(
            title = "test",
            rentables = listOf(
                RentableHolder(
                    remember{ mutableStateOf(false) },
                    Rentable("test0", RentableType.CAR)
                ),
                RentableHolder(
                    remember{mutableStateOf(false)},
                    Rentable("test1", RentableType.CAR)
                )
            )
        )
    }
}