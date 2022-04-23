package com.umut.soysal.spacedelivery.core.navigation.bottomnavigation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.umut.soysal.spacedelivery.R
import com.umut.soysal.spacedelivery.core.theme.SpaceXColor
import com.umut.soysal.spacedelivery.core.theme.typography
import com.umut.soysal.spacedelivery.core.ui.component.SubTitleText

@Composable
fun BottomNavigationMenu(navController: NavController? = null) {
    val items = listOf(
        BottomNavigationScreens.StationTravelScreen,
        BottomNavigationScreens.FavoriteStationScreen
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp)
    ) {

        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 2.dp, color = SpaceXColor.surface),
            backgroundColor = SpaceXColor.background,
            contentColor = SpaceXColor.surface
        ) {
            val navBackStackEntry by navController!!.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon!!),
                            contentDescription = ""
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id =item.title!!),
                            style = typography.subtitle1
                        )
                    },
                    selectedContentColor =  colorResource(id = R.color.blue),
                    unselectedContentColor =  SpaceXColor.surface,
                    alwaysShowLabel = true,
                    selected = currentRoute == item.route,
                    onClick = {
                        navController!!.navigate(item.route) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}