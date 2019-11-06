package com.example.punkapi.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.punkapi.model.BeersResponse
import com.example.punkapi.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class BeersViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val progressObservable = MutableLiveData<Boolean>()
    private val beersObservable: MutableLiveData<List<BeersResponse>> = MutableLiveData()
    private val errorObservable:MutableLiveData<Boolean> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    fun getBeers() {
        compositeDisposable.add(
            repository.getBeers()
                .doOnSubscribe { progressObservable.postValue(true) }
                .doOnError { progressObservable.value = false }
                .subscribe({ beers ->
                    beersObservable.value = beers
                    progressObservable.value = false
                }, {errorObservable.value = true})
        )
    }

    fun getBeersObservable() = beersObservable
    fun getProgressObservable() = progressObservable
    fun getErrorObservable() = errorObservable

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()

    }


}