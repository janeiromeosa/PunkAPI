package com.example.punkapi.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BeersResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("abv")
    val abv: Double,
    @SerializedName("ingredients")
    val ingredients: Ingredients,
    @SerializedName("food_pairing")
    val foodPairing: List<String>
):Parcelable

class CompareByNames {
    companion object : Comparator<BeersResponse>{

        override fun compare(o1: BeersResponse, o2: BeersResponse): Int {
            return o1.name.compareTo(o2.name)
        }
    }
}

class CompareByRating {
    companion object : Comparator<BeersResponse>{

        override fun compare(o1: BeersResponse, o2: BeersResponse): Int {
            return o1.abv.compareTo(o2.abv)
        }
    }
}