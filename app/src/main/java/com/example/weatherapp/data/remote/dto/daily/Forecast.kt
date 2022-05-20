package com.example.weatherapp.data.remote.dto.daily


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Forecast(
    @SerialName("date")
    val date: String,
    @SerialName("maxTemp")
    val maxTemp: Double,
    @SerialName("maxWindSpeed")
    val maxWindSpeed: Double,
    @SerialName("minTemp")
    val minTemp: Double,
    @SerialName("precipAccum")
    val precipAccum: Double,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("windDir")
    val windDir: Double
)