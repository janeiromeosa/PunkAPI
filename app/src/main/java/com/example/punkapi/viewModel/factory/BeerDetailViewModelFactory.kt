package com.example.punkapi.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.punkapi.repository.Repository
import com.example.punkapi.viewModel.BeersViewModel

class BeerDetailViewModelFactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BeersViewModel(repository) as T
    }
}