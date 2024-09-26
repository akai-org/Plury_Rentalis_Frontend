package org.akai.pluryrenatlisapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.akai.pluryrenatlisapp.data.Rent
import org.akai.pluryrenatlisapp.ui.theme.PluryRenatlisAppTheme

@Composable
fun RentableList(
    title: String,
    rents: List<RentableHolder>,
    modifier: Modifier = Modifier,
    content: @Composable (rents: List<RentableHolder>) -> Unit = {}
) {
    Column (
        modifier = modifier.height(MaterialTheme.typography.titleMedium.fontSize.value.dp + 10.dp + (rents.size * 50).dp)
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
            content(rents)
        }
    }
}



data class RentableHolder(
    var checked: MutableState<Boolean>,
    val rent: Rent,
    val onCheckedChange: (Boolean) -> Unit = { checked.value = it }
)

@Preview
@Composable
fun RentableListPreview() {
    PluryRenatlisAppTheme {
        RentableList(
            title = "test",
            rents = listOf(
                RentableHolder(
                    remember{ mutableStateOf(false) },
                    Rent (
                        uuid = "test",
                        userName = "Adam Małysz",
                        rentableName = "test1",
                    )
                ),
                RentableHolder(
                    remember{mutableStateOf(false)},
                    Rent (
                        uuid = "test",
                        rentableName = "test2"
                    )
                ),
                RentableHolder(
                    remember{mutableStateOf(false)},
                    Rent (
                        uuid = "test",
                        rentableName = "test2"
                    )
                ),
                RentableHolder(
                    remember{mutableStateOf(false)},
                    Rent (
                        uuid = "test",
                        rentableName = "test2"
                    )
                ),
                RentableHolder(
                    remember{mutableStateOf(false)},
                    Rent (
                        uuid = "test",
                        rentableName = "test2"
                    )
                )
            )
        )
    }
}