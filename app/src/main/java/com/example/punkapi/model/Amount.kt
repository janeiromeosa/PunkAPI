package com.example.punkapi.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Amount(
    @SerializedName("value")
    val value: Double,
    @SerializedName("unit")
    val unit: String
):Parcelable