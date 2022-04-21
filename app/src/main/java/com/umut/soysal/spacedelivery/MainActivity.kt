package com.umut.soysal.spacedelivery

import android.os.Bundle
import androidx.activity.compose.setContent
import com.umut.soysal.spacedelivery.core.base.BaseActivity
import com.umut.soysal.spacedelivery.core.theme.SpaceDeliveryTheme

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceDeliveryTheme {
               //Navigation
            }
        }
    }
}