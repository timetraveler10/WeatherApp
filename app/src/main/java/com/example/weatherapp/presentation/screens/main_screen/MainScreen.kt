package com.example.weatherapp.presentation.screens.main_screen

import SymbolToImage
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.CalendarViewWeek
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.m3weather.presentation.components.utils.DetailsItem
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.domain.model.HourlyWeather
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.presentation.MainViewModel
import com.example.weatherapp.presentation.WeatherEvents
import com.example.weatherapp.presentation.screens.destinations.DailyForecastScreenDestination
import com.example.weatherapp.presentation.screens.destinations.SettingScreenDestination
import com.example.weatherapp.utils.DateUtils.parseTime
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.ZonedDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Destination
@RootNavGraph(start = true)
fun MainScreen(mainViewModel: MainViewModel, navigator: DestinationsNavigator) {

    Log.d("VMCheck@MainScreen", "$mainViewModel ")

    val state = mainViewModel.state

    if (state.isLoading) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier.size(150.dp))

        }
    }

    if (state.weather != null) {

        CurrentWeatherCard(
            state.weather,
            navigator,
            onRefresh = { mainViewModel.onEvent(WeatherEvents.GetWeather) })
    }


}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeatherCard(weather: Weather, navigator: DestinationsNavigator, onRefresh: () -> Unit) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .animateContentSize()
            .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp)
    ) {

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(vertical = 10.dp, horizontal = 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Icon(
                imageVector = SymbolToImage(symbol = weather.current.symbol),
                contentDescription = null,
                modifier = Modifier.size(110.dp), tint = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = weather.current.symbolPhrase,
                fontWeight = FontWeight.Medium,
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = parseTime(
                    "EEEE",
                    weather.current.time
                ),
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .6f)
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "${weather.current.temperature.toInt()}°",
                fontWeight = FontWeight.Medium,
                fontSize = 70.sp,
                color = MaterialTheme.colorScheme.onBackground, modifier = Modifier.offset(x = 8.dp)
            )


            Divider(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .6f),
                modifier = Modifier.padding(horizontal = 15.dp)
            )

            DetailsGrid(weather = weather.current)

            Spacer(modifier = Modifier.height(2.dp))

            HourlyWeatherCard(hourlyWeather = weather.hourlyWeather)

            Spacer(modifier = Modifier.height(4.dp))

            NavigationCardItem(
                onClick = { navigator.navigate(DailyForecastScreenDestination) },
                title = "Daily Forecast",
                vectorIcon = Icons.Default.CalendarViewWeek, BorderStroke(
                    width = 0.4.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            NavigationCardItem(
                onClick = { onRefresh() },
                title = "Refresh",
                vectorIcon = Icons.Default.Refresh, BorderStroke(
                    width = 0.4.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            NavigationCardItem(
                onClick = { navigator.navigate(SettingScreenDestination) },
                title = "Settings",
                vectorIcon = Icons.Default.Settings, BorderStroke(
                    width = 0.4.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                )
            )


        }

    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyWeatherCard(hourlyWeather: List<HourlyWeather>) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp), shape = RoundedCornerShape(15.dp)
    ) {

        LazyRow(modifier = Modifier.padding(4.dp)) {
            items(hourlyWeather) {

                HourlyWeatherListItem(hourlyWeather = it)

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyWeatherListItem(hourlyWeather: HourlyWeather) {

    Surface(
        border = BorderStroke(0.5.dp, color = MaterialTheme.colorScheme.outline.copy(alpha = .6f)),
        shape = RoundedCornerShape(12.dp), modifier = Modifier.padding(horizontal = 2.dp)
    ) {

        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = parseTime(
                    time = ZonedDateTime.parse(hourlyWeather.time).hour.toString()
                        .replace("\\s".toRegex(), ""), format = "hh aa"
                ),
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(2.dp))


            Icon(
                imageVector = SymbolToImage(symbol = hourlyWeather.symbol),
                contentDescription = null, modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))

            Text(text = "${hourlyWeather.temperature}°", fontWeight = FontWeight.Medium)
        }


    }

}

@Composable
fun DetailsGrid(weather: CurrentWeather) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        DetailRow(
            detailsItem1 = DetailsItem(
                icon = R.drawable.ic_cloud2,
                title = "Cloudiness",
                subtitle = "${weather.cloudiness}%"
            ),
            detailsItem2 = DetailsItem(
                icon = R.drawable.ic_thermometer,
                title = "Feels Like",
                subtitle = "${weather.feelsLikeTemp}°"
            )
        )

        DetailRow(
            detailsItem1 = DetailsItem(
                icon = R.drawable.ic_wind,
                title = "Wind Speed",
                subtitle = "${weather.windSpeed} m/s"
            ),
            detailsItem2 = DetailsItem(
                icon = R.drawable.ic_drop,
                title = "RainProp",
                subtitle = "${weather.rainProb}%"
            )
        )

    }
}

@Composable
fun DetailRow(detailsItem1: DetailsItem, detailsItem2: DetailsItem) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        DetailsItem(
            ImageVector.vectorResource(id = detailsItem1.icon),
            text = detailsItem1.title, value = detailsItem1.subtitle
        )

        DetailsItem(
            imageVector = ImageVector.vectorResource(detailsItem2.icon),
            text = detailsItem2.title, value = detailsItem2.subtitle
        )

    }

}

@Composable
fun DetailsItem(imageVector: ImageVector, text: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(5.dp)
                .size(30.dp)
        )

        Column {

            Text(
                text = text,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = value,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationCardItem(
    onClick: () -> Unit,
    title: String,
    vectorIcon: ImageVector,
    borderStroke: BorderStroke
) {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        border = borderStroke,
        shape = RoundedCornerShape(10.dp), onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 15.dp, horizontal = 8.dp)
        ) {


            Icon(
                imageVector = vectorIcon,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Medium)

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.ArrowRight,
                contentDescription = null
            )

        }

    }

}
