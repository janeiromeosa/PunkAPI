package com.example.punkapi.di.beer

import com.example.punkapi.view.BeersActivity
import com.example.punkapi.di.app.AppComponent
import dagger.Component

@BeersScope
@Component(modules = [BeersModule::class],dependencies = [AppComponent::class])
interface BeersComponent {
    fun inject(beersActivity: BeersActivity)
}