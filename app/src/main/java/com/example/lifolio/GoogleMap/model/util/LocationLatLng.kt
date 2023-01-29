package com.example.lifolio.GoogleMap.model.util

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationLatLng(
    val latitude: Float,
    val longitude: Float
): Parcelable