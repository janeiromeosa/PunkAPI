package com.example.punkapi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.punkapi.MyApp
import com.example.punkapi.R
import com.example.punkapi.di.beerdetail.BeerDetailModule
import com.example.punkapi.di.beerdetail.DaggerBeerDetailComponent
import com.example.punkapi.model.BeersResponse
import com.example.punkapi.viewModel.BeerDetailViewModel
import kotlinx.android.synthetic.main.item_beer_detail.*
import javax.inject.Inject


class BeerDetailActivity : AppCompatActivity() {

    lateinit var beer: BeersResponse

    @Inject
    lateinit var beerDetailViewModel: BeerDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_beer_detail)
        getDependencies()

        beerDetailViewModel.getBeersObservable().observe(this, Observer {
            tv_description.text = it.description
            tv_hops.text =
                it.ingredients.hops.joinToString(separator = ",", transform = { hop ->
                    hop.name
                })
            tv_malt.text =
                it.ingredients.malt.joinToString(separator = ",", transform = { malt ->
                    malt.name
                })
            tv_food_pairings.text = it.foodPairing.joinToString(",")
        })
        beerDetailViewModel.getBeerDetail(intent)
    }

    private fun getDependencies() {
        DaggerBeerDetailComponent.builder().appComponent((application as MyApp).component())
            .beerDetailModule(
                BeerDetailModule(this)
            ).build().inject(this)

    }

    companion object {
        const val BEER_KEY = "beer"
    }
}