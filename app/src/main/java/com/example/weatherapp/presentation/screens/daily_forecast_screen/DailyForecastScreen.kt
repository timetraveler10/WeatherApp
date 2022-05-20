package com.example.weatherapp.presentation.screens.daily_forecast_screen

import SymbolToImage
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.DailyWeather
import com.example.weatherapp.presentation.MainViewModel
import com.example.weatherapp.presentation.WeatherEvents
import com.example.weatherapp.presentation.screens.destinations.MainScreenDestination
import com.example.weatherapp.presentation.screens.main_screen.NavigationCardItem
import com.example.weatherapp.utils.DateUtils
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun DailyForecastScreen(
    mainViewModel: MainViewModel,
    navigator: DestinationsNavigator
) {

    LaunchedEffect(key1 = true) {
        mainViewModel.onEvent(WeatherEvents.GetDailyWeather)
    }
    Log.d("VMCheck@DailyScreen", "$mainViewModel ")

    val state = mainViewModel.dailyWeatherState
    val isExpanded by remember { mutableStateOf(false) }


    if (state.isLoading) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
            CircularProgressIndicator(modifier = Modifier.size(250.dp))

        }
    }
    if (state.weather != null) {
        val decayAnimationSpec = rememberSplineBasedDecay<Float>()
        val scrollBehavior = remember(decayAnimationSpec) {
            TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
        }
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                SmallTopAppBar(
                    title = { Text("Daily Forecast") },
                    navigationIcon = {
                        IconButton(onClick = { navigator.navigate(MainScreenDestination) },
                            content = {
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
                    contentPadding = PaddingValues(
                        top = innerPadding.calculateTopPadding(),
                        bottom = innerPadding.calculateBottomPadding(), start = 8.dp, end = 8.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    items(state.weather) {
                        WeekForecastListItem(
                            it,
                            isExpanded = isExpanded,
                            onItemClick = { isExpanded != isExpanded })
                    }
                }
            }
        )
    }

}


@Composable
private fun WeekForecastListItem(
    dailyWeather: DailyWeather,
    isExpanded: Boolean,
    onItemClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .animateContentSize()
            .clickable(onClick = onItemClick),
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            width = .3.dp,
            color = MaterialTheme.colorScheme.outline.copy(alpha = .4f)
        )
    ) {

        Column(modifier = Modifier.padding(vertical = 10.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .wrapContentHeight(),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = SymbolToImage(dailyWeather.symbol),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.weight(.3f, true))
                Text(
                    text = DateUtils.setDateInWeek(date = dailyWeather.date),
                    fontSize = 21.sp,
                    modifier = Modifier.weight(6f),
                    color = MaterialTheme.colorScheme.onSurface
                )


                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 23.sp)) {
                            append("${dailyWeather.maxTemp}°")
                        }
                        withStyle(style = SpanStyle(fontSize = 23.sp)) {
                            append('/')
                        }
                        withStyle(style = SpanStyle(fontSize = 20.sp)) {
                            append("${dailyWeather.minTemp}°")
                        }
                    }
                )

            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(animationSpec = tween(200)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 6.dp)
            ) {
                Divider(
                    modifier = Modifier.padding(horizontal = 6.dp),
                    color = MaterialTheme.colorScheme.outline.copy(alpha = .6f),
                    thickness = .6.dp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    WeeklyItemDetails(
                        title = "WindSpeed",
                        value = "${dailyWeather.maxWindSpeed}m/s",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                    )

                }

            }
        }

    }

}

@Composable
fun WeeklyItemDetails(title: String, value: String, icon: ImageVector) {

    Row(
        verticalAlignment = CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null, modifier = Modifier.size(27.dp)
        )
        Spacer(modifier = Modifier.width(2.dp))
        Column() {

            Text(text = title, fontSize = 13.sp, overflow = TextOverflow.Ellipsis)
            Text(text = value)

        }
    }

}