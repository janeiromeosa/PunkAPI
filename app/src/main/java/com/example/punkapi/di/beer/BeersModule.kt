package com.example.punkapi.di.beer

import androidx.lifecycle.ViewModelProviders
import com.example.punkapi.view.BeersActivity
import com.example.punkapi.repository.Repository
import com.example.punkapi.viewModel.BeersViewModel
import com.example.punkapi.viewModel.factory.BeersViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class BeersModule(private val beersActivity: BeersActivity) {

    @Provides
    @BeersScope
    fun provideBeersViewModelFactory(repository: Repository): BeersViewModelFactory {
        return BeersViewModelFactory(repository)
    }

    @Provides
    @BeersScope
    fun provideBeersViewModel(beersViewModelFactory: BeersViewModelFactory): BeersViewModel {

        return ViewModelProviders.of(beersActivity, beersViewModelFactory)
            .get(BeersViewModel::class.java)
    }
}