package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.local.LocationEntity
import com.example.weatherapp.data.remote.dto.current.Current
import com.example.weatherapp.data.remote.dto.current.CurrentWeatherDto
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.domain.model.DailyWeather
import com.example.weatherapp.domain.model.HourlyWeather
import com.google.android.gms.maps.model.LatLng

fun Current.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        temperature = temperature,
        cloudiness = cloudiness.toInt(),
        feelsLikeTemp = feelsLikeTemp,
        rainProb = precipProb.toInt(),
        rainRate = precipRate.toInt(),
        symbol = symbol,
        symbolPhrase = symbolPhrase,
        thunderProb = thunderProb.toInt(),
        windDirString = windDirString,
        windSpeed = windSpeed,
        uvIndex = uvIndex.toInt(),
        dewPoint = dewPoint,
        time = time,
        pressure = pressure
    )
}

fun com.example.weatherapp.data.remote.dto.daily.Forecast.toDailyWeather(): DailyWeather {
    return DailyWeather(
        date = date,
        maxTemp = maxTemp.toInt(),
        minTemp = minTemp.toInt(),
        maxWindSpeed = maxWindSpeed.toInt(),
        symbol = symbol,
    )
}

fun com.example.weatherapp.data.remote.dto.hourly.Forecast.toHourlyWeather(): HourlyWeather {
    return HourlyWeather(
        feelsLikeTemp = feelsLikeTemp.toInt(),
        rainProb = precipProb.toInt(),
        temperature = temperature.toInt(),
        windSpeed = windSpeed.toInt(),
        symbol = symbol, time = time
    )

}

