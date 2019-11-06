package com.example.punkapi

import android.content.Intent
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.punkapi.model.*
import com.example.punkapi.view.BeerDetailActivity
import com.example.punkapi.viewModel.BeerDetailViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BeerDetailViewModelTest {

    @Rule
    @JvmField
    var testRule: TestRule = InstantTaskExecutorRule()


    private lateinit var viewModel: BeerDetailViewModel

    @Mock
    lateinit var intent: Intent

    @Mock
    lateinit var bundle: Bundle

    private val beersListObserver: Observer<BeersResponse> = mock()
    private val errorObserver: Observer<Boolean> = mock()

    private val amount = Amount(0.0, "unit")
    private val hopList = mutableListOf(Hop("name", amount, "add", "attribute"))
    private val maltList = mutableListOf(Malt("name", amount))
    private val ingredients = Ingredients(maltList, hopList, "yeast")
    private val foodPairing = listOf("food pairing 1", "food pairing 2")
    private val beer = BeersResponse(1, "name", "desc", "image.com", 0.0, ingredients, foodPairing)

    @Before
    fun setup() {
        viewModel = BeerDetailViewModel()

        viewModel.getBeersObservable().observeForever(beersListObserver)
        viewModel.getErrorObservable().observeForever(errorObserver)
        `when`(intent.extras).thenReturn(bundle)

    }

    @Test
    fun getBeerDetailReturnsSuccessfully() {
        `when`(bundle.containsKey(BeerDetailActivity.BEER_KEY)).thenReturn(true)
        `when`(intent.getParcelableExtra<BeersResponse>(BeerDetailActivity.BEER_KEY)).thenReturn(
            beer
        )

        viewModel.getBeerDetail(intent)

        verify(beersListObserver, times(1)).onChanged(beer)
        verify(errorObserver, times(1)).onChanged(false)
    }

    @Test
    fun getBeersReturnsError() {
        `when`(bundle.containsKey(BeerDetailActivity.BEER_KEY)).thenReturn(false)

        viewModel.getBeerDetail(intent)

        verify(beersListObserver, times(0)).onChanged(ArgumentMatchers.any())
        verify(errorObserver, times(1)).onChanged(true)
    }

}