package com.example.punkapi.di.app

import com.example.punkapi.net.BeersService
import com.example.punkapi.repository.RemoteDataSource
import com.example.punkapi.repository.RemoteDataSourceImpl
import com.example.punkapi.repository.Repository
import com.example.punkapi.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteDataSource): Repository {
        return RepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(beersService: BeersService): RemoteDataSource {
        return RemoteDataSourceImpl(beersService)
    }
}