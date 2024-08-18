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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp

@Composable
fun UtilityColumnWithLogo(
    modifier: Modifier = Modifier,
    logo: @Composable () -> Unit = { PersonAsLogo() },
    utility: @Composable () -> Unit,
) {
    val minorBackgroundColor = MaterialTheme.colorScheme.tertiary
    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            shape = RoundedCornerShape(32.dp),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .drawBehind {
                    drawCircle(
                        color = minorBackgroundColor,
                        radius = center.y * 10f,
                        center = center.copy(y = -center.y * 9f)
                    )
                }
        ) {
            logo()
        }
        utility()
    }
}

@Composable
private fun PersonAsLogo() =
    Icon(
        imageVector = Icons.Rounded.Person,
        contentDescription = "Account Icon",
        tint = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    )