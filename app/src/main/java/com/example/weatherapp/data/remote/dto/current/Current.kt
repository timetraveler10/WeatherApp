package com.example.weatherapp.data.remote.dto.current


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Current(
    @SerialName("cloudiness")
    val cloudiness: Double,
    @SerialName("dewPoint")
    val dewPoint: Double,
    @SerialName("feelsLikeTemp")
    val feelsLikeTemp: Double,
    @SerialName("precipProb")
    val precipProb: Double,
    @SerialName("precipRate")
    val precipRate: Double,
    @SerialName("pressure")
    val pressure: Double,
    @SerialName("relHumidity")
    val relHumidity: Double,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("symbolPhrase")
    val symbolPhrase: String,
    @SerialName("temperature")
    val temperature: Double,
    @SerialName("thunderProb")
    val thunderProb: Double,
    @SerialName("time")
    val time: String,
    @SerialName("uvIndex")
    val uvIndex: Double,
    @SerialName("visibility")
    val visibility: Double,
    @SerialName("windDir")
    val windDir: Double,
    @SerialName("windDirString")
    val windDirString: String,
    @SerialName("windGust")
    val windGust: Double,
    @SerialName("windSpeed")
    val windSpeed: Double
)