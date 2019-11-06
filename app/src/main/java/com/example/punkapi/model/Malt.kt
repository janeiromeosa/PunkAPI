package com.example.punkapi.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Malt(
    @SerializedName("name")
    val name: String,
    @SerializedName("amount")
    val amount: Amount
):Parcelable