package com.example.weatherapp.presentation.screens.settings_screen

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.weatherapp.presentation.screens.destinations.AboutScreenDestination
import com.example.weatherapp.presentation.screens.destinations.MainScreenDestination
import com.example.weatherapp.presentation.screens.destinations.MapScreenDestination
import com.example.weatherapp.presentation.screens.main_screen.NavigationCardItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun SettingScreen(navigator: DestinationsNavigator) {

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SmallTopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = {

                        navigator.popBackStack(route = MainScreenDestination , inclusive = false)
                    }, content = {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    })
                },
                scrollBehavior = scrollBehavior
            )
        },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    NavigationCardItem(
                        onClick = { navigator.navigate(MapScreenDestination) },
                        title = "Location Settings",
                        vectorIcon = Icons.Default.Map,
                        borderStroke = BorderStroke(
                            width = 0.dp,
                            color = Color.Unspecified
                        )
                    )
                    Divider()

                }
                item {
                    NavigationCardItem(
                        onClick = {  },
                        title = "Preferred Unit",
                        vectorIcon = Icons.Default.Map,
                        borderStroke = BorderStroke(
                            width = 0.dp,
                            color = Color.Unspecified
                        )
                    )

                    Divider()

                }
                item {
                    NavigationCardItem(
                        onClick = {
                            navigator.navigate(
                                AboutScreenDestination,
                                onlyIfResumed = true
                            )
                        },
                        title = "About",
                        vectorIcon = Icons.Default.Info,
                        borderStroke = BorderStroke(
                            width = 0.dp,
                            color = Color.Unspecified
                        )
                    )
                }
            }
        }
    )

}

