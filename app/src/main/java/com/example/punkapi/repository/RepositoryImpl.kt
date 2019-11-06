package com.example.punkapi.repository

import com.example.punkapi.model.BeersResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource):
    Repository {

    override fun getBeers(): Single<List<BeersResponse>> {
        return remoteDataSource.getBeers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()   )
    }


}