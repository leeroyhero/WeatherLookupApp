package com.bogdanov.weatherlookupapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bogdanov.weatherlookupapp.ui.navigation.AppNavGraph
import com.bogdanov.weatherlookupapp.ui.theme.WeatherLookupAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherLookupAppTheme {
                AppNavGraph()
            }
        }
    }
}