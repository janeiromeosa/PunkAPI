package com.example.punkapi.di.beerdetail

import androidx.lifecycle.ViewModelProviders
import com.example.punkapi.repository.Repository
import com.example.punkapi.view.BeerDetailActivity
import com.example.punkapi.viewModel.BeersViewModel
import com.example.punkapi.viewModel.factory.BeersViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class BeerDetailModule(private val beerDetailActivity: BeerDetailActivity) {

    @Provides
    @BeerDetailScope
    fun provideBeersViewModelFactory(repository: Repository): BeersViewModelFactory {
        return BeersViewModelFactory(repository)
    }

    @Provides
    @BeerDetailScope
    fun provideBeersViewModel(beersViewModelFactory: BeersViewModelFactory): BeersViewModel {

        return ViewModelProviders.of(beerDetailActivity, beersViewModelFactory)
            .get(BeersViewModel::class.java)
    }
}