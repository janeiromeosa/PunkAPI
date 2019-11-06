package com.example.punkapi.repository

import com.example.punkapi.model.BeersResponse
import com.example.punkapi.net.BeersService
import io.reactivex.Single

import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val beersService: BeersService) : RemoteDataSource {
    override fun getBeers(): Single<List<BeersResponse>> {
        return beersService.getBeers()
    }


}