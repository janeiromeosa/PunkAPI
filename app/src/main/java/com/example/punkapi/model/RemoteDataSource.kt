package com.example.punkapi.model

import com.example.punkapi.BASE_URL
import com.example.punkapi.net.BeersListService
import io.reactivex.Maybe
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource : DataSource {

    private val beersListService: BeersListService by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        retrofit.create(BeersListService::class.java)
    }
    override fun getBeersFromList(): Single<List<DataBeersList>> {
        return beersListService.getBeers().flatMap{Single.just(it)}
    }
}