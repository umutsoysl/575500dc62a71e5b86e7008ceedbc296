package com.umut.soysal.spacedelivery.feature

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.umut.soysal.spacedelivery.core.navigation.bottomnavigation.BottomNavigationHost
import com.umut.soysal.spacedelivery.core.navigation.bottomnavigation.BottomNavigationMenu

@Composable
fun HomeScreen(
    navigationController: NavController? = null,
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationMenu(navController = navController) }
    ) {

        BottomNavigationHost(navController = navController)
    }
}