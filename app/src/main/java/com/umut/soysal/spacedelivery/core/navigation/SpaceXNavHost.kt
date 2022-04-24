package com.umut.soysal.spacedelivery.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.umut.soysal.spacedelivery.feature.HomeScreen
import com.umut.soysal.spacedelivery.feature.space.CreateSpaceScreen
import com.umut.soysal.spacedelivery.feature.splash.SplashScreen

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SpaceXNavHost() {
    val navigation = rememberNavController()

    NavHost(
        navController = navigation,
        startDestination = Screens.SplashScreen.route
    ) {
        //Splash screen
        composable(
            route = Screens.SplashScreen.route
        ) {
            SplashScreen(
                navigationController = navigation
            )
        }

        //Create space screen
        composable(
            route = Screens.CreateSpaceScreen.route
        ) {
            CreateSpaceScreen(
                navigationController = navigation
            )
        }

        //Home screen
        composable(
            route = Screens.HomeScreen.route
        ) {
            HomeScreen(
                navigationController = navigation
            )
        }
    }
}