package com.umut.soysal.spacedelivery.feature.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

@Composable
fun FavoriteStationScreen(
    navigationController: NavController? = null,
    viewModel: FavoriteStationViewModel
) {

    val favoriteStationList by viewModel.stationListState.collectAsState()



    LaunchedEffect(Unit) {
        viewModel.getFavoriteStationList()
    }
}