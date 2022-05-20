package com.example.weatherapp.data.remote.dto.hourly


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Forecast(
    @SerialName("feelsLikeTemp")
    val feelsLikeTemp: Double,
    @SerialName("precipAccum")
    val precipAccum: Double,
    @SerialName("precipProb")
    val precipProb: Double,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("temperature")
    val temperature: Double,
    @SerialName("time")
    val time: String,
    @SerialName("windDir")
    val windDir: Int,
    @SerialName("windDirString")
    val windDirString: String,
    @SerialName("windGust")
    val windGust: Double,
    @SerialName("windSpeed")
    val windSpeed: Double
)