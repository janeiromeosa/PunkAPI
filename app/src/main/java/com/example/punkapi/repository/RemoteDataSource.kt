package com.example.punkapi.repository

import com.example.punkapi.model.BeersResponse
import io.reactivex.Single

interface RemoteDataSource {
    fun getBeers():Single<List<BeersResponse>>
}