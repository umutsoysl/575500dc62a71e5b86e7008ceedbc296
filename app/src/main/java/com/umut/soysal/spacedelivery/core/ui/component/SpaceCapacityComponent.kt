package com.umut.soysal.spacedelivery.core.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.umut.soysal.spacedelivery.core.theme.SpaceXColor

@Composable
fun SpaceCapacityComponent(
    spaceName: String = "",
    capacity: Int = 0,
    time: Int = 0
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(
            vertical = 16.dp
        ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TitleText(
            text = spaceName
        )

        BorderBoxTextComponent(
            capacity.toString()
        )

        BorderBoxTextComponent(
            "${time}s"
        )
    }
}

@Composable
fun BorderBoxTextComponent(
    text: String = ""
) {
    Box( Modifier
        .border(width = 2.dp, color = SpaceXColor.surface)
    ) {
        SubTitleText(
            text,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 20.dp)
        )
    }
}