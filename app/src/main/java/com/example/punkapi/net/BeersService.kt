package com.example.punkapi.net

import com.example.punkapi.common.BEER_ENDPOINT
import com.example.punkapi.model.BeersResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BeersService{
    @GET(BEER_ENDPOINT)
    fun getBeers(): Single<List<BeersResponse>>
}

