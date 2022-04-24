package com.umut.soysal.spacedelivery.core.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.umut.soysal.spacedelivery.R
import com.umut.soysal.spacedelivery.core.db.entity.StationEntity
import com.umut.soysal.spacedelivery.core.theme.SpaceXColor
import kotlinx.coroutines.launch


@Composable
fun SliderItemComponent(
    item: StationEntity,
    clickFavoriteButtonValue: (Boolean) -> Unit = {}
) {

    val favoriteIcon = rememberSaveable { mutableStateOf(R.drawable.empty_star) }

    if (item.isFavorite) {
        favoriteIcon.value = R.drawable.favorite
    } else {
        favoriteIcon.value = R.drawable.empty_star
    }

    Row(
        modifier = Modifier
            .size(height = 300.dp, width = 250.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = SpaceXColor.surface,
                    shape = RoundedCornerShape(5.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    InputText(item.capacity.toString())
                    InputText("242EUS")
                }

                IconButton(
                    onClick = {
                        if (favoriteIcon.value == R.drawable.empty_star) {
                            favoriteIcon.value = R.drawable.favorite
                            clickFavoriteButtonValue(true)
                        } else {
                            favoriteIcon.value = R.drawable.empty_star
                            clickFavoriteButtonValue(false)
                        }
                    },
                    modifier = Modifier
                        .padding(start = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = favoriteIcon.value),
                        contentDescription = "search",
                        colorFilter = ColorFilter.tint(
                            SpaceXColor.surface
                        ),
                        modifier = Modifier
                            .size(21.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            HeaderTitleText(item.name.toString())

            Spacer(modifier = Modifier.size(16.dp))

            OutlinedButton(
                onClick = {

                },
                modifier = Modifier
                    .padding(top = 16.dp, end = 16.dp)
                    .wrapContentWidth(),
                border = BorderStroke(2.dp, SpaceXColor.surface),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Color.Transparent
                )

            ) {
                ButtonText(
                    text = stringResource(id = R.string.travel),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Spacer(modifier = Modifier.size(16.dp))
        }

    }
}