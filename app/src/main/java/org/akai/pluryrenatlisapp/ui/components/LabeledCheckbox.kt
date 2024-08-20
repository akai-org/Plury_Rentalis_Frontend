package org.akai.pluryrenatlisapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LabeledCheckbox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = modifier
        ){
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
            )

            Text(
                text = label,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                maxLines = 1,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

}

@Preview
@Composable
fun CheckboxWithLabelsPreview() {
    LabeledCheckbox(
        label = "Checkbox label",
        checked = false,
        onCheckedChange = {}
    )
}