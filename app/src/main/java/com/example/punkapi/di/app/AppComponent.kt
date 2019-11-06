package com.example.punkapi.di.app

import com.example.punkapi.MyApp
import com.example.punkapi.repository.Repository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(myApp: MyApp)

    fun repository(): Repository
}