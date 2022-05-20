package com.example.weatherapp.domain.use_cases

import com.example.weatherapp.data.local.DatastoreRepo
import com.example.weatherapp.data.mappers.toCurrentWeather
import com.example.weatherapp.data.mappers.toDailyWeather
import com.example.weatherapp.data.mappers.toHourlyWeather
import com.example.weatherapp.data.remote.dto.current.CurrentWeatherDto
import com.example.weatherapp.data.remote.dto.daily.DailyForecastDto
import com.example.weatherapp.data.remote.dto.hourly.HourlyForecastDto
import com.example.weatherapp.data.remote.service.KtorClientFactory
import com.example.weatherapp.domain.model.DailyWeather
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.utils.Constants.CURRENT_END_POINT
import com.example.weatherapp.utils.Constants.DAILY_WEATHER_END_POINT
import com.example.weatherapp.utils.Constants.HOURLY_WEATHER_END_POINT
import com.example.weatherapp.utils.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDailyWeatherUseCase @Inject constructor(
    private val ktorClientFactory: KtorClientFactory, private val datastoreRepo: DatastoreRepo
) {

    operator fun invoke(): Flow<Resource<List<DailyWeather>>> {
        return flow {

            emit(Resource.Loading(true))

            try {
                val client = ktorClientFactory.build()
                val lngLatStr = datastoreRepo.readLngLat().flowOn(Dispatchers.Default).catch {
                    emit(Resource.Error<List<DailyWeather>>(message = it.message))
                }.first()

                val dailyWeather = client.get<DailyForecastDto> {
                    url(DAILY_WEATHER_END_POINT)
                    parameter(key = "location" , value = lngLatStr)
                }

                emit(
                    Resource.Success(
                        data = dailyWeather.forecast.map { it.toDailyWeather() }
                    )
                )

            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message))
            }

        }
    }
}