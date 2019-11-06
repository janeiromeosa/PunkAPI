package com.example.punkapi

import com.example.punkapi.model.BeersResponse

interface BeersClickListener {
    fun onBeerClicked(beer:BeersResponse)
}