package com.umut.soysal.spacedelivery.core.util

fun Double.format(digits: Int) = "%.${digits}f".format(this).toDouble()