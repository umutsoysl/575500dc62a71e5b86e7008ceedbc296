package com.umut.soysal.spacedelivery.core.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.umut.soysal.spacedelivery.core.theme.SpaceXColor
import com.umut.soysal.spacedelivery.core.theme.typography

@Composable
fun BigTitleText(text: String = "", modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = SpaceXColor.surface,
        style = typography.h2
    )
}