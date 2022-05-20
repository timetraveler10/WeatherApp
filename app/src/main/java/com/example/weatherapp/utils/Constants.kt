package com.example.weatherapp.utils

object Constants {

    const val TOKEN =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9wZmEuZm9yZWNhLmNvbVwvYXV0aG9yaXplXC90b2tlbiIsImlhdCI6MTYzODE0Njk0MSwiZXhwIjo5OTk5OTk5OTk5LCJuYmYiOjE2MzgxNDY5NDEsImp0aSI6IjE3MmJlOWEyNTJiMjEzZGMiLCJzdWIiOiJodXNzZW5hYmRib21hIiwiZm10IjoiWERjT2hqQzQwK0FMamxZVHRqYk9pQT09In0.KpetodAlioHYqHW0rGrLxzoxUIbhCrH_blkZ43AkssM"

    private const val BASE_URL = "https://pfa.foreca.com"

    /*********************************MainEndPoints**********************************/

    const val CURRENT_END_POINT = "$BASE_URL/api/v1/current/"
    const val DAILY_WEATHER_END_POINT = "$BASE_URL/api/v1/forecast/daily/"
    const val HOURLY_WEATHER_END_POINT = "$BASE_URL/api/v1/forecast/hourly/"

    /********************************************************************************/
}