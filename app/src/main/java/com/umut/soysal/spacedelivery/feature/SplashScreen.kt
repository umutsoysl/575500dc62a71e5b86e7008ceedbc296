package com.umut.soysal.spacedelivery.feature

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.umut.soysal.spacedelivery.core.navigation.Screens
import com.umut.soysal.spacedelivery.core.theme.SpaceXColor
import com.umut.soysal.spacedelivery.core.ui.component.BigTitleText
import com.umut.soysal.spacedelivery.R

@Composable
fun SplashScreen(
    navigationController: NavController? = null,
    alphaAnimationTargetValue: Float = 0f,
    alphaAnimationDurationMillis: Int = 1500,
) {
    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SpaceXColor.background),
            contentAlignment = Alignment.Center
        ) {

            var animationTargetValue by remember { mutableStateOf(alphaAnimationTargetValue) }

            val alpha: Float by animateFloatAsState(
                targetValue = animationTargetValue,
                animationSpec = tween(alphaAnimationDurationMillis),
                finishedListener = {
                    navigationController?.navigate(Screens.CreateSpaceScreen.route) {
                        popUpTo(Screens.CreateSpaceScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )

            Column {
                BigTitleText(text = stringResource(id = R.string.app_name),
                    modifier = Modifier.alpha(alpha)
                )
            }

            LaunchedEffect(Unit) {
                animationTargetValue = 1f
            }

        }
    }
}