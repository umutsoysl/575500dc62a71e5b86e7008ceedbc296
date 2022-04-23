package com.umut.soysal.spacedelivery.feature.space

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.umut.soysal.spacedelivery.R
import com.umut.soysal.spacedelivery.core.navigation.Screens
import com.umut.soysal.spacedelivery.core.theme.SpaceXColor
import com.umut.soysal.spacedelivery.core.ui.component.HeaderTitleText
import com.umut.soysal.spacedelivery.core.ui.component.InputText
import com.umut.soysal.spacedelivery.core.ui.component.SeekBarComponent
import com.umut.soysal.spacedelivery.core.ui.component.TitleText

@Composable
fun CreateSpaceScreen(
    navigationController: NavController? = null,
    viewModel: CreateSpaceViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var spaceName by rememberSaveable { mutableStateOf("") }

    var spacePoint by rememberSaveable { mutableStateOf(3) }

    var speedPoint by rememberSaveable { mutableStateOf(1) }

    var durabilityPoint by rememberSaveable { mutableStateOf(1) }

    var capacityPoint by rememberSaveable { mutableStateOf(1) }

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
                    Column {
                        Row(
                            modifier = Modifier.padding(bottom = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            HeaderTitleText(
                                text = stringResource(id = R.string.space_point),
                                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                            )

                            Box(
                                Modifier
                                    .border(width = 2.dp, color = SpaceXColor.surface)
                            ) {
                                HeaderTitleText(
                                    text = spacePoint.toString(),
                                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                                )
                            }
                        }

                        Divider(
                            color = SpaceXColor.surface,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                        )
                    }
                }

                item{
                    Spacer(modifier = Modifier.size(30.dp))
                }

                item {
                    TextField(
                        value = spaceName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(width = 2.dp, color = SpaceXColor.surface)
                            .padding(
                                vertical = 2.dp,
                                horizontal = 3.dp
                            )
                            .background(SpaceXColor.background),
                        onValueChange = {
                            if (it.length <= 30) spaceName = it
                        },
                        textStyle = TextStyle(
                            color = SpaceXColor.surface,
                            fontWeight = FontWeight.Bold,
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
                        label = {
                            InputText(
                                stringResource(id = R.string.name)
                            )
                        }
                    )
                }

                item{
                    Spacer(modifier = Modifier.size(30.dp))
                }

                item {
                    SeekBarComponent(
                        title = stringResource(id = R.string.durability),
                        onSelectionValue = { value ->
                            durabilityPoint = value
                            spacePoint = durabilityPoint + speedPoint + capacityPoint
                        }
                    )
                }

                item {
                    SeekBarComponent(
                        title = stringResource(id = R.string.speed),
                        onSelectionValue = { value ->
                            speedPoint = value
                            spacePoint = durabilityPoint + speedPoint + capacityPoint
                        }
                    )
                }

                item {
                    SeekBarComponent(
                        title = stringResource(id = R.string.capacity),
                        onSelectionValue = { value ->
                            capacityPoint = value
                            spacePoint = durabilityPoint + speedPoint + capacityPoint
                        }
                    )
                }

                item{
                    Spacer(modifier = Modifier.size(40.dp))
                }

                item {
                    OutlinedButton(
                        onClick = {
                            if(spacePoint>15) {
                                Toast.makeText(context, context.getString(R.string.space_point_warning), Toast.LENGTH_SHORT).show()
                            } else{
                                viewModel.insertSpace(
                                    spaceName,
                                    speedPoint,
                                    capacityPoint,
                                    durabilityPoint
                                )
                                navigationController?.navigate(Screens.HomeScreen.route)
                            }
                        },
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth(),
                        border= BorderStroke(2.dp, SpaceXColor.surface),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent, contentColor =  Color.Transparent)

                    ) {
                        TitleText(text = stringResource(id = R.string.next),
                        modifier = Modifier.padding(vertical = 16.dp))
                    }
                }

            }
        }
    }
}