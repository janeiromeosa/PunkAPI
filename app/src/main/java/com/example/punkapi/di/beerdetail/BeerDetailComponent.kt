package com.example.punkapi.di.beerdetail

import com.example.punkapi.di.app.AppComponent
import com.example.punkapi.view.BeerDetailActivity
import dagger.Component

@BeerDetailScope
@Component(modules = [BeerDetailModule::class], dependencies = [AppComponent::class])
interface BeerDetailComponent {
    fun inject(beersActivity: BeerDetailActivity)
}