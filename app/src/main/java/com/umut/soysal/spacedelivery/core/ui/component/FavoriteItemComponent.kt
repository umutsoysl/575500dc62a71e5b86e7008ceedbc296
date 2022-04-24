package com.umut.soysal.spacedelivery.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.umut.soysal.spacedelivery.R
import com.umut.soysal.spacedelivery.core.db.entity.StationEntity
import com.umut.soysal.spacedelivery.core.theme.SpaceXColor

@Composable
fun FavoriteItemComponent(
    station: StationEntity,
    clickFavoriteButtonValue: (StationEntity) -> Unit = {}
) {

    val favoriteIcon = rememberSaveable { mutableStateOf(R.drawable.favorite) }

    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(width = 2.dp, color = SpaceXColor.surface),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
        ) {
            SubTitleText(station.name)
            InputText("242EUS")
        }

        IconButton(
            onClick = {
                if (favoriteIcon.value == R.drawable.empty_star) {
                    favoriteIcon.value = R.drawable.favorite
                } else {
                    favoriteIcon.value = R.drawable.empty_star
                }
                clickFavoriteButtonValue(station)
            },
            modifier = Modifier
                .padding(end = 16.dp)
        ) {
            Image(
                painter = painterResource(id = favoriteIcon.value),
                contentDescription = "favorite",
                colorFilter = ColorFilter.tint(
                    SpaceXColor.surface
                ),
                modifier = Modifier
                    .size(21.dp)
            )
        }
    }
}