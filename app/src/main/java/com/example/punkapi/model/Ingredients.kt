package com.example.punkapi.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingredients(
    @SerializedName("malt")
    val malt: List<Malt>,
    @SerializedName("hops")
    val hops: List<Hop>,
    @SerializedName("yeast")
    val yeast: String
):Parcelable