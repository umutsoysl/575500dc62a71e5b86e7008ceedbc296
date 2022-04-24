package com.umut.soysal.spacedelivery.core.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with

val typography = Typography(
    h1 = TextStyle(fontSize = 64.sp, fontWeight = FontWeight.Black),
    h2 = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Black),
    h3 = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
    h4 = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
    h5 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
    h6 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
    body1 = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal),
    body2 = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.ExtraBold),
    subtitle1 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
    subtitle2 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
    button = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
    overline = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
    caption = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Black)
)