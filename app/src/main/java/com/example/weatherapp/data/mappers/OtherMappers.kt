package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.local.LocationEntity
import com.google.android.gms.maps.model.LatLng

fun LatLng.toLocationEntity(): LocationEntity {
    return LocationEntity(lat = latitude , lng = longitude)
}
fun LocationEntity.toLatLng(): LatLng {
    return LatLng(lat , lng)
}