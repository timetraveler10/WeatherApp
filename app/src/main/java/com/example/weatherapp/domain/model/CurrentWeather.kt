package com.example.weatherapp.domain.model

data class CurrentWeather(
    val temperature: Double,
    val cloudiness: Int,
    val feelsLikeTemp: Double,
    val rainProb: Int,
    val rainRate: Int,
    val symbol: String,
    val symbolPhrase: String,
    val thunderProb: Int,
    val windDirString: String,
    val windSpeed: Double ,
    val uvIndex: Int,
    val time: String,
    val pressure: Double,
    val dewPoint: Double,
)
