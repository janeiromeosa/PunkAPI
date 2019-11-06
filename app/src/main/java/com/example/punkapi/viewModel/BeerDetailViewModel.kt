package com.example.punkapi.viewModel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.punkapi.model.BeersResponse
import com.example.punkapi.view.BeerDetailActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class BeerDetailViewModel @Inject constructor() : ViewModel() {

    private val beerObservable: MutableLiveData<BeersResponse> = MutableLiveData()
    private val errorObservable: MutableLiveData<Boolean> = MutableLiveData()


    fun getBeerDetail(intent: Intent) {
        if (intent.extras?.containsKey(BeerDetailActivity.BEER_KEY) == true) {
            errorObservable.value = false
            beerObservable.value = intent.getParcelableExtra(BeerDetailActivity.BEER_KEY)

        } else {
            errorObservable.value = true
        }
    }

    fun getBeersObservable() = beerObservable
    fun getErrorObservable() = errorObservable

}