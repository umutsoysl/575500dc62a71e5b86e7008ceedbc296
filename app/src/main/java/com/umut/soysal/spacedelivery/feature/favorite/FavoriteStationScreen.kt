package com.umut.soysal.spacedelivery.feature.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.umut.soysal.spacedelivery.R
import com.umut.soysal.spacedelivery.core.theme.SpaceXColor
import com.umut.soysal.spacedelivery.core.ui.component.FavoriteItemComponent
import com.umut.soysal.spacedelivery.core.ui.component.HeaderTitleText

@Composable
fun FavoriteStationScreen(
    navigationController: NavController? = null,
    viewModel: FavoriteStationViewModel = hiltViewModel()
) {

    val favoriteStationList by viewModel.stationListState.collectAsState()

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
                    Column(
                        modifier = Modifier.padding(bottom = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        HeaderTitleText(
                            text = stringResource(id = R.string.favorites),
                            modifier = Modifier.padding(bottom = 16.dp),
                        )

                        Divider(
                            color = SpaceXColor.surface,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                        )
                    }
                }

                items(favoriteStationList.size){ index->
                    FavoriteItemComponent(station = favoriteStationList[index],
                    clickFavoriteButtonValue = { station ->
                        viewModel.updateFavoriteStation(station)
                    })
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getFavoriteStationList()
    }
}