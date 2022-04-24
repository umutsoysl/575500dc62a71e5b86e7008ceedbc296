package com.umut.soysal.spacedelivery.core.navigation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.umut.soysal.spacedelivery.feature.favorite.FavoriteStationScreen
import com.umut.soysal.spacedelivery.feature.station.StationTravelScreen

@ExperimentalPagerApi
@Composable
fun BottomNavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.StationTravelScreen.route
    ) {

        composable(
            route = BottomNavigationScreens.StationTravelScreen.route
        ) {
            StationTravelScreen(
                navigationController = navController
            )
        }

        composable(
            route = BottomNavigationScreens.FavoriteStationScreen.route
        ) {
            FavoriteStationScreen(
                navigationController = navController
            )
        }
    }
}