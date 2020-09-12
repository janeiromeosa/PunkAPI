package com.example.punkapi.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.punkapi.BeersClickListener
import com.example.punkapi.MyApp
import com.example.punkapi.R
import com.example.punkapi.di.beer.BeersModule
import com.example.punkapi.di.beer.DaggerBeersComponent
import com.example.punkapi.model.BeersResponse
import com.example.punkapi.model.CompareByNames
import com.example.punkapi.model.CompareByRating
import com.example.punkapi.viewModel.BeersViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
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
            showData(it)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mu_sort_names_ascending -> {
                Toast.makeText(applicationContext, "Names sorted in Ascending Order", Toast.LENGTH_LONG).show()
                beersViewModel.getBeersObservable().observe(this, Observer {
                    sortNamesInAscending(it)
                    showData(it)
                })
                true
            }
            R.id.mu_sort_ratings_ascending -> {
                Toast.makeText(applicationContext, "Ratings sorted in Ascending Order", Toast.LENGTH_LONG).show()
                beersViewModel.getBeersObservable().observe(this, Observer {
                    sortRatingsInAscending(it)
                    showData(it)
                })
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun sortRatingsInAscending(list: List<BeersResponse>): List<BeersResponse>{
        Collections.sort(list, CompareByRating)
        return list
    }

    private fun sortNamesInAscending(list: List<BeersResponse>): List<BeersResponse>{
        Collections.sort(list, CompareByNames)
        return list
    }

    private fun showData(it: List<BeersResponse>){
        adapter.data.clear()
        adapter.data.addAll(it)
        adapter.notifyDataSetChanged()
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
