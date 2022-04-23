package com.umut.soysal.spacedelivery.feature.station

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun StationTravelScreen(
    navigationController: NavController? = null,
    stationViewModel: StationViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        stationViewModel.fetchStationList()
    }
}