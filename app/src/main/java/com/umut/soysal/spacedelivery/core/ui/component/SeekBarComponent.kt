package com.umut.soysal.spacedelivery.core.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.umut.soysal.spacedelivery.core.theme.SpaceXColor


@Composable
fun SeekBarComponent(
    title: String = "",
    onSelectionValue: (Int) -> Unit = {}
) {
    var sliderPosition by remember { mutableStateOf(1f) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TitleText(title)
            InputText(": ${sliderPosition.toInt()}", modifier = Modifier.padding(start = 8.dp, top = 2.dp))
        }
        Slider(
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it
            },
            valueRange = 0f..15f,
            onValueChangeFinished = {
                if(sliderPosition.toInt() == 0) {
                    sliderPosition = 1f
                }
                onSelectionValue(sliderPosition.toInt())
            },
            steps = 15,
            colors = SliderDefaults.colors(
                thumbColor = SpaceXColor.surface,
                activeTrackColor = SpaceXColor.surface,
                inactiveTrackColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
    }

}