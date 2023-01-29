package com.example.lifolio.GoogleMap.model

import android.os.Parcelable
import com.example.lifolio.GoogleMap.model.util.LocationLatLng
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResult(
    val fullAddress: String,
    val name: String,
    val locationLatLng: LocationLatLng
): Parcelable