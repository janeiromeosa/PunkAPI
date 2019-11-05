package com.example.punkapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.punkapi.model.DataBeersList
import com.example.punkapi.view.BeersListAdapter
import com.example.punkapi.viewModel.BeersListUseCase
import com.example.punkapi.viewModel.BeersListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter = BeersListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_beer_list.layoutManager = LinearLayoutManager(this)
        rv_beer_list.adapter = adapter

        val homeViewModel = ViewModelProviders.of(this).get(BeersListViewModel::class.java)

        val observer = Observer<List<DataBeersList>>{
            if (it!= null)
                adapter.setData(it)
        }

        homeViewModel.getBeers()
        homeViewModel.getRepositoryObservable().observe(this, observer)
    }


}
