package com.example.punkapi.model

import io.reactivex.Single

class Repository(private val remoteDataSource: DataSource): DataSource {

    override fun getBeersFromList(): Single<List<DataBeersList>> {
        return remoteDataSource.getBeersFromList()
    }
}