package com.example.weatherapp.domain.model

data class DailyWeather(
    val date: String,
    val maxTemp: Int,
    val maxWindSpeed: Int,
    val minTemp: Int,
    val symbol: String,
    )