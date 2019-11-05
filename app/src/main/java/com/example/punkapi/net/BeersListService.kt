package com.example.punkapi.net

import com.example.punkapi.BEER_ENDPOINT
import com.example.punkapi.model.DataBeersList
import io.reactivex.Single
import retrofit2.http.GET

interface BeersListService{
    @GET(BEER_ENDPOINT)
    fun getBeers(): Single<List<DataBeersList>>
}

