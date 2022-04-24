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

@Composable
fun HeaderTitleText(text: String = "", modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = SpaceXColor.surface,
        style = typography.h4
    )
}

@Composable
fun TitleText(text: String = "", modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = SpaceXColor.surface,
        style = typography.h5
    )
}

@Composable
fun SubTitleText(text: String = "", modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = SpaceXColor.surface,
        style = typography.subtitle1
    )
}

@Composable
fun InputText(text: String = "", modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = SpaceXColor.error,
        style = typography.body1
    )
}

@Composable
fun ButtonText(text: String = "", modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = SpaceXColor.surface,
        style = typography.button
    )
}
