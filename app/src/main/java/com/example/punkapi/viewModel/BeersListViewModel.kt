package com.example.punkapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.punkapi.model.DataBeersList
import com.example.punkapi.model.DataSource
import com.example.punkapi.model.RemoteDataSource
import com.example.punkapi.model.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BeersListViewModel: ViewModel(), BeersListUseCase{

    private val loadingLiveData = MutableLiveData<Boolean>()
    private val contentLiveData: MutableLiveData<List<DataBeersList>> = MutableLiveData()
    private lateinit var repository: DataSource
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getBeers() {
        repository = Repository(remoteDataSource = RemoteDataSource())

        compositeDisposable.add(repository.getBeersFromList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {loadingLiveData.value = true }
            .doOnEvent{_, _ -> loadingLiveData.value = false}
            .subscribe({contentLiveData.value = it},{it.printStackTrace()}))
    }

    override fun onCleared() {
        super.onCleared()

    }

    fun getRepositoryObservable(): MutableLiveData<List<DataBeersList>> = contentLiveData
//    fun getObserableBoolean(): LiveData<Boolean> = loadingLiveData
}