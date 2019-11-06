package com.example.punkapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.punkapi.BeersClickListener
import com.example.punkapi.R
import com.example.punkapi.model.BeersResponse
import com.example.punkapi.view.BeersActivity.Companion.BEER_KEY
import kotlinx.android.synthetic.main.item_beer_detail.*


class BeerDetailActivity : AppCompatActivity() {

    lateinit var beer: BeersResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_beer_detail)

        getIncomingIntent()
    }

    private fun getIncomingIntent(){
        beer = intent.getParcelableExtra(BEER_KEY)

        Toast.makeText(this,beer.ingredients.malt[0].name,Toast.LENGTH_SHORT).show()

        val description = beer.description

        val hops = beer.ingredients.hops.get(0).name
        val malt = beer.ingredients.malt.get(0).name
        val foodPairings = beer.foodPairing

        setVariables(description, hops, malt, foodPairings)
    }

    private fun setVariables(description: String, hops: String, malt: String, foodPairings: List<String>){
        val description1 = findViewById<TextView>(R.id.tv_description)
        description1.setText(description)
        val hops1 = findViewById<TextView>(R.id.tv_infomation_on_hops)
        hops1.setText(hops)
        val malt1 = findViewById<TextView>(R.id.tv_infomation_on_malt)
        malt1.setText(malt)
        val foodPairings1: ListView = findViewById(R.id.lv_food_pairings)
        val adapter = ArrayAdapter(this, R.layout.list_beer_detail, foodPairings)
        foodPairings1.adapter = adapter

    }
}