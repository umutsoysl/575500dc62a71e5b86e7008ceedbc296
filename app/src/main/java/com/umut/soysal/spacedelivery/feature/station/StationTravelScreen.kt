package com.umut.soysal.spacedelivery.feature.station

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.umut.soysal.spacedelivery.R
import com.umut.soysal.spacedelivery.core.theme.SpaceXColor
import com.umut.soysal.spacedelivery.core.ui.component.*
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

    val currentDistance by stationViewModel.distanceState.collectAsState()

    val sumDistanceState by stationViewModel.sumDistanceState.collectAsState()

    val currentDamageTime by stationViewModel.currentDamageTime.collectAsState()

    val state = rememberPagerState(pageCount = stationList.size, initialPage=0)

    val scope = rememberCoroutineScope()

    var searchKeyword by remember { mutableStateOf("") }

    val startTravel by stationViewModel.startTravel.collectAsState()


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
                        time = currentDamageTime
                    )
                }

                item {
                    Box(modifier = Modifier
                        .padding(vertical = 16.dp)
                        .border(width = 2.dp, color = SpaceXColor.surface)) {
                        TextField(
                            value = searchKeyword,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(SpaceXColor.background),
                            onValueChange = {
                                if (it.length <= 30){
                                    stationViewModel.fetchStationList(it)
                                    searchKeyword = it
                                }
                            },
                            textStyle = TextStyle(
                                color = SpaceXColor.surface,
                                fontWeight = FontWeight.Normal,
                                fontSize = 18.sp
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                backgroundColor = SpaceXColor.background,
                                cursorColor = SpaceXColor.surface
                            ),
                            leadingIcon = {
                                Icon(Icons.Outlined.Search, null,
                                    tint = SpaceXColor.surface)
                            },
                            label = {
                                InputText(
                                    text = stringResource(id = R.string.search),
                                )
                            }
                        )
                    }
                }

                if(!stationList.isNullOrEmpty()) {
                    val time = dsValue.div(1000).minus(1)
                    if(startTravel && currentDistance>0 && time-currentDamageTime>sumDistanceState) {
                        scope.launch {
                            stationViewModel.calculateDistance(stationList[state.currentPage], true)
                            stationViewModel.ugsValueUpdate(stationList[state.currentPage].need)
                            if(state.currentPage + 1<stationList.size) {
                                state.animateScrollToPage(state.currentPage + 1)
                            } else {
                                stationViewModel.setWorldStation()
                            }
                        }
                    }

                    item {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            IconButton(
                                onClick = {
                                    if (state.currentPage - 1 >= 0) {
                                        scope.launch {
                                            state.animateScrollToPage(state.currentPage - 1)
                                            stationViewModel.calculateDistance(stationList[state.currentPage])
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
                                    distance = currentDistance,
                                    isTravel = startTravel,
                                    clickFavoriteButtonValue = { isFavorite->
                                        stationViewModel.updateFavoriteStation(
                                            stationList[index].id,
                                            isFavorite
                                        )
                                        stationList[index].isFavorite = isFavorite
                                    },
                                    clickTravel =  { station->
                                        stationViewModel.travel()
                                    }
                                )
                            }

                            IconButton(
                                onClick = {
                                    if (state.currentPage + 1 < stationList.size) {
                                        scope.launch {
                                            state.animateScrollToPage(state.currentPage + 1)
                                            stationViewModel.calculateDistance(stationList[state.currentPage])
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