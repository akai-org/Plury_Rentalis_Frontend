package org.akai.pluryrenatlisapp.ui.compositions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.akai.pluryrenatlisapp.data.Rent
import org.akai.pluryrenatlisapp.ui.components.RentableHolder
import org.akai.pluryrenatlisapp.ui.components.RentableList
import org.akai.pluryrenatlisapp.ui.theme.PluryRenatlisAppTheme

@Composable
fun ChoosingRentsComposition(
    rents: List<Rent>,
    buttonText: String = "",
    choosable: Boolean = true,
    onBack: () -> Unit = {},
    onAccept: suspend (List<Rent>) -> Unit = {},
    content: @Composable (List<RentableHolder>) -> Unit = {},

) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    var isToValidation by remember { mutableStateOf(false) }
    val toRent = remember { mutableStateListOf<Rent>() }

    val groups = rents.groupBy { it.rentableType }.toSortedMap()
    Column(
        modifier =
        if (isToValidation)
            Modifier
                .fillMaxSize()
                .blur(8.dp)
        else
            Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val minorBackgroundColor = MaterialTheme.colorScheme.tertiary
        Box(
            modifier = Modifier
                .drawBehind {
                    drawCircle(
                        color = minorBackgroundColor,
                        radius = 5000f,
                        center = center.copy(y = -4800f)
                    )
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = onBack
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
//                    .padding(horizontal = 10.dp)
                            .size(100.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Wybierz",
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            groups.keys.forEachIndexed { index, type ->
                Button(onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(index)
                    }
                })
                {
                    Text(
                        text = type,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(9.1f / 10f),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
            ) {
                items(groups.entries.toList(), key = { it.value }) {
                    RentableList(
                        modifier = Modifier.padding(vertical = 10.dp),
                        title = it.key,
                        rents = it.value.sortedBy { rent -> rent.rentableName }.map { rent ->
                            val checked = remember { mutableStateOf(false) }
                            RentableHolder(
                                checked = checked,
                                rent
                            ) { recordChecked ->
                                checked.value = recordChecked
                                if (checked.value)
                                    toRent.add(rent)
                                else
                                    toRent.remove(rent)
                            }
                        },
                    ) { list ->
                        content(list)
                    }
                }
            }
        }
        if (choosable)
            Button(
                onClick = {
                    isToValidation = true
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 10.dp),
            ) {
                Text(
                    text = "$buttonText${if (toRent.size > 0) "(${toRent.size})" else ""}",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                )
            }
    }

    if (isToValidation) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            FloatingValidation(
                rents = toRent,
                accept = onAccept,
                reject = {
                    isToValidation = false
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewChoosingRentsComposition() {
    val rents = remember { mutableStateListOf<Rent>() }
    rents.addAll(listOf(
        Rent(),
        Rent(),
        Rent(),
        Rent(),
        Rent(),
        Rent(),
        Rent(),
        Rent(),
        Rent(),
        Rent(),
        Rent(rentableType = "Auto"),
        Rent(rentableType = "Auto"),
        Rent(rentableType = "Auto"),
        Rent(rentableType = "Auto"),
        Rent(rentableType = "Auto"),
        Rent(rentableType = "Auto"),
        Rent(rentableType = "Auto"),
        Rent(rentableType = "Auto"),
        Rent(rentableType = "Auto"),
        Rent(rentableType = "Auto"),
        Rent(rentableType = "Sowa"),
        Rent(rentableType = "Sowa"),
        Rent(rentableType = "Sowa"),
        Rent(rentableType = "Sowa"),
        Rent(rentableType = "Sowa"),
    ))
    PluryRenatlisAppTheme {
        ChoosingRentsComposition(
            rents = rents,
            buttonText = "Wybierz"
        )
    }
}