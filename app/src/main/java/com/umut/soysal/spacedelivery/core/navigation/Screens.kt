package com.umut.soysal.spacedelivery.core.navigation

sealed class Screens(val route: String) {
    object SplashScreen : Screens("SplashScreen")
    object CreateSpaceScreen : Screens("CreateSpaceScreen")
    object HomeScreen : Screens("HomeScreen")
}