package com.umut.soysal.spacedelivery.feature.station

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.umut.soysal.spacedelivery.core.theme.SpaceXColor
import com.umut.soysal.spacedelivery.core.ui.component.HeaderTitleText
import com.umut.soysal.spacedelivery.core.ui.component.SliderItemComponent
import com.umut.soysal.spacedelivery.core.ui.component.SpaceCapacityComponent
import com.umut.soysal.spacedelivery.core.ui.component.SpacePropertiesComponent
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun StationTravelScreen(
    navigationController: NavController? = null,
    stationViewModel: StationViewModel = hiltViewModel()
) {

    val spaceData by stationViewModel.spaceDataState.collectAsState()

    val ugsValue by stationViewModel.ugsState.collectAsState()

    val eusValue by stationViewModel.eusState.collectAsState()

    val dsValue by stationViewModel.dsState.collectAsState()

    val damageCapacityValue by stationViewModel.damageCapacityState.collectAsState()

    val stationList by stationViewModel.stationListState.collectAsState()

    val currentStation by stationViewModel.currentStation.collectAsState()

    val state = rememberPagerState(pageCount = stationList.size)

    val scope = rememberCoroutineScope()

    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SpaceXColor.background)
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ) {

                item {
                    SpacePropertiesComponent(
                        ugsValue = ugsValue,
                        eusValue = eusValue,
                        dsValue = dsValue
                    )
                }

                item {
                    SpaceCapacityComponent(
                        spaceName = spaceData.name,
                        capacity = damageCapacityValue,
                        time = 50
                    )
                }

                if(!stationList.isNullOrEmpty()) {
                    item {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(
                                onClick = {
                                    if (state.currentPage - 1 >= 0) {
                                        scope.launch {
                                            state.animateScrollToPage(state.currentPage - 1)
                                        }
                                    }
                                } ,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                            ) {
                                Icon(Icons.Outlined.KeyboardArrowLeft, null, tint = SpaceXColor.surface)
                            }

                            HorizontalPager(
                                state = state,
                                modifier = Modifier
                                    .size(height = 300.dp, width = 250.dp)
                            ) { index ->
                                SliderItemComponent(
                                    item = stationList[index],
                                    clickFavoriteButtonValue = { isFavorite->
                                        stationViewModel.updateFavoriteStation(
                                            stationList[index].id,
                                            isFavorite
                                        )
                                        stationList[index].isFavorite = isFavorite
                                    }
                                )
                            }

                            IconButton(
                                onClick = {
                                    if (state.currentPage + 1 < stationList.size) {
                                        scope.launch {
                                            state.animateScrollToPage(state.currentPage + 1)
                                        }
                                    }
                                } ,
                                modifier = Modifier
                                    .padding(start = 8.dp)
                            ) {
                                Icon(Icons.Outlined.KeyboardArrowRight, null, tint = SpaceXColor.surface)
                            }
                        }
                    }
                } else {
                    item {
                        CircularProgressIndicator()
                    }
                }
                
                item { 
                    Spacer(modifier = Modifier.size(30.dp))
                }

                item {
                    Row( modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        HeaderTitleText(currentStation.name)
                    }
                }
            }
        }
    }


    LaunchedEffect(Unit) {
        stationViewModel.fetchStationList()
        stationViewModel.getSpace()
    }
}