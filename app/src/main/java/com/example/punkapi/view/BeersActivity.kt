package com.example.punkapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.punkapi.BeersClickListener
import com.example.punkapi.MyApp
import com.example.punkapi.R
import com.example.punkapi.di.beer.BeersModule
import com.example.punkapi.di.beer.DaggerBeersComponent
import com.example.punkapi.model.BeersResponse
import com.example.punkapi.viewModel.BeersViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class BeersActivity : AppCompatActivity() {

    private lateinit var adapter: BeersAdapter

    @Inject
    lateinit var beersViewModel: BeersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()
        getDependencies()

        beersViewModel.getBeers()

        beersViewModel.getBeersObservable().observe(this, Observer {
            adapter.data.clear()
            adapter.data.addAll(it)
            adapter.notifyDataSetChanged()
        })

        beersViewModel.getProgressObservable().observe(this, Observer {
            if (it == true) {
                pb_loading.visibility = View.VISIBLE
            } else {
                pb_loading.visibility = View.GONE
            }
        })

        beersViewModel.getErrorObservable().observe(this, Observer {
            if (it == true) {
                error_message_container.visibility = View.VISIBLE
            } else {
                error_message_container.visibility = View.GONE
            }
        })

        btnRetry.setOnClickListener {
            beersViewModel.getBeers()
            error_message_container.visibility = View.GONE

        }

    }


    private fun setUpRecyclerView() {
        adapter = BeersAdapter(mutableListOf(), object : BeersClickListener {
            override fun onBeerClicked(beer: BeersResponse) {
                val intent = Intent(this@BeersActivity, BeerDetailActivity::class.java)
                intent.putExtra(BEER_KEY, beer)
                startActivity(intent)
            }

        })

        rv_beer_list.layoutManager = LinearLayoutManager(this)
        rv_beer_list.adapter = adapter
    }

    private fun getDependencies() {
        DaggerBeersComponent.builder().appComponent((application as MyApp).component()).beersModule(
            BeersModule(this)
        ).build().inject(this)

    }

    companion object {
        const val BEER_KEY = "beer"
    }
}
