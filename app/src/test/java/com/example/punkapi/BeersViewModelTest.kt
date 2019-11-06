package com.example.punkapi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.punkapi.model.*
import com.example.punkapi.repository.Repository
import com.example.punkapi.viewModel.BeersViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class BeersViewModelTest {

    @Rule
    @JvmField
    var testRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository

    private lateinit var viewModel: BeersViewModel

    private val beersListObserver: Observer<List<BeersResponse>> = mock()
    private val progressObserver: Observer<Boolean> = mock()
    private val errorObserver: Observer<Boolean> = mock()

    private val amount = Amount(0.0, "unit")
    private val hopList = mutableListOf(Hop("name", amount, "add", "attribute"))
    private val maltList = mutableListOf(Malt("name", amount))
    private val ingredients = Ingredients(maltList, hopList, "yeast")
    private val foodPairing = listOf("food pairing 1", "food pairing 2")
    private val beer = BeersResponse(1, "name", "desc", "image.com", 0.0, ingredients, foodPairing)

    @Before
    fun setup() {
        viewModel = BeersViewModel(repository)

        viewModel.getBeersObservable().observeForever(beersListObserver)
        viewModel.getProgressObservable().observeForever(progressObserver)
        viewModel.getErrorObservable().observeForever(errorObserver)

    }

    @Test
    fun getBeersReturnsSuccessfully() {

        val beersLIst = mutableListOf(beer)

        `when`(repository.getBeers()).thenReturn(Single.just(beersLIst))

        viewModel.getBeers()

        verify(beersListObserver, times(1)).onChanged(beersLIst)
        verify(progressObserver, times(1)).onChanged(true)
        verify(progressObserver, times(1)).onChanged(false)
        verify(errorObserver, times(0)).onChanged(false)
    }

    @Test
    fun getBeersReturnsError() {
        val errorMessage = "Error message"

        `when`(repository.getBeers()).thenReturn(
            Single.error(
                RuntimeException(errorMessage)
            )
        )

        viewModel.getBeers()

        verify(beersListObserver, times(0)).onChanged(ArgumentMatchers.anyList())
        verify(progressObserver, times(1)).onChanged(true)
        verify(progressObserver, times(1)).onChanged(false)
        verify(errorObserver, times(1)).onChanged(true)
    }

}