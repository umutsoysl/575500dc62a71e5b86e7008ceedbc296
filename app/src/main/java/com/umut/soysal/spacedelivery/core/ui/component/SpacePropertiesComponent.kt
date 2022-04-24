package com.umut.soysal.spacedelivery.core.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.umut.soysal.spacedelivery.core.theme.SpaceXColor

@Composable
fun SpacePropertiesComponent(
    ugsValue: Int = 0,
    eusValue: Int = 0,
    dsValue: Int = 0,
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            SpacePropertiesItem(
                title = "UGS: ",
                value = ugsValue
            )
            SpacePropertiesItem(
                title = "EUS: ",
                value = eusValue
            )
            SpacePropertiesItem(
                title = "DS: ",
                value = dsValue
            )
        }

        Divider(
            color = SpaceXColor.surface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(2.dp)
        )
    }
}

@Composable
fun SpacePropertiesItem(
    title: String = "",
    value: Int = 0
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        HeaderTitleText(
            text = title
        )

        TitleText(
            text = value.toString()
        )
    }
}