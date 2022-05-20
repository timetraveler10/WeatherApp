package com.example.weatherapp.domain.model

data class HourlyWeather(
    val feelsLikeTemp: Int,
    val rainProb: Int,
    val temperature: Int,
    val windSpeed: Int,
    val symbol: String,
    val time: String,

    )
