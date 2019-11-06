package com.example.punkapi.repository

import com.example.punkapi.model.BeersResponse
import io.reactivex.Single

interface Repository {
    fun getBeers():Single<List<BeersResponse>>
}