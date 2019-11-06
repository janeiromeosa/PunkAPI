package com.example.punkapi

import android.app.Application
import com.example.punkapi.di.app.AppComponent
import com.example.punkapi.di.app.DaggerAppComponent
import com.example.punkapi.di.app.NetworkModule
import com.example.punkapi.di.app.RepositoryModule

class MyApp:Application() {

    override fun onCreate() {
        super.onCreate()
        component()
    }

 fun component(): AppComponent =
     DaggerAppComponent.builder()
     .networkModule(NetworkModule())
     .repositoryModule(RepositoryModule())
     .build()
}