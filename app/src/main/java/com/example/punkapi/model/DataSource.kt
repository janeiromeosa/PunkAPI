package com.example.punkapi.model

import io.reactivex.Maybe
import io.reactivex.Single

interface DataSource {
    fun getBeersFromList(): Single<List<DataBeersList>>
}