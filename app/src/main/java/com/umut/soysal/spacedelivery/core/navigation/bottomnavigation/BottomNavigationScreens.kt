package com.umut.soysal.spacedelivery.core.navigation.bottomnavigation

import com.umut.soysal.spacedelivery.R

sealed class BottomNavigationScreens(
    val route: String,
    val title: Int? = null,
    val icon: Int? = null
) {
    object StationTravelScreen :
        BottomNavigationScreens("StationTravelScreen", R.string.station, R.drawable.space)

    object FavoriteStationScreen :
        BottomNavigationScreens("FavoriteStationScreen", R.string.favorites, R.drawable.favorite)
}