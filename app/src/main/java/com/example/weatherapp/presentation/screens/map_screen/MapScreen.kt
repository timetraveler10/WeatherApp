package com.example.weatherapp.presentation.screens.map_screen

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.data.mappers.toLatLng
import com.example.weatherapp.data.mappers.toLocationEntity
import com.example.weatherapp.presentation.MainViewModel
import com.example.weatherapp.presentation.WeatherEvents
import com.example.weatherapp.presentation.screens.destinations.MainScreenDestination
import com.example.weatherapp.presentation.screens.destinations.SettingScreenDestination
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun MapScreen(
    navigator: DestinationsNavigator,
    mainViewModel: MainViewModel
) {

    val markerState = rememberMarkerState(
        position = mainViewModel.state.locationEntity.toLatLng()
    )

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {

            MapScreenTopAppBar(
                navigator = navigator,
                onDoneClicked = {
                    mainViewModel.onEvent(WeatherEvents.PersistLocation(markerState.position.toLocationEntity()))
                    navigator.popBackStack()
                    navigator.popBackStack(MainScreenDestination , inclusive = false)
                })
        }
    ) { paddingValues ->

        GoogleMap(
            modifier = Modifier.padding(paddingValues),
            onMapLongClick = { markerState.position = it },
            content =  {
                Marker(state = markerState)
            }

        )

    }

}


@Composable
private fun MapScreenTopAppBar(onDoneClicked: () -> Unit, navigator: DestinationsNavigator) {

    SmallTopAppBar(
        title = { Text(text = "Pick A Location") }, actions = {
            IconButton(onClick = { onDoneClicked() },
                content = {
                Icon(imageVector = Icons.Default.Done, contentDescription = null)
            }
            )
        },
        navigationIcon = {
            IconButton(onClick = { navigator.navigate(SettingScreenDestination) } ,
                content = { Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)})
        }
    )


}