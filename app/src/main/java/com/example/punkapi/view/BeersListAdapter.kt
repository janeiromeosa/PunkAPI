package com.example.punkapi.view

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.punkapi.R
import com.example.punkapi.model.DataBeersList

class BeersListAdapter: RecyclerView.Adapter<BeersListAdapter.BeersViewHolder>(){

    private val data = mutableListOf<DataBeersList>()

    fun setData(dataBeers: List<DataBeersList>){
        dataBeers.let {
            data.clear()
            data.addAll(dataBeers)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false)
        return BeersViewHolder(view)
    }

    override fun getItemCount()= data.size

    override fun onBindViewHolder(holder: BeersViewHolder, position: Int) {
        holder.name.text = data[position].name
        holder.abv.text = data[position].id.toString()
        holder.beerImage.setImageURI(Uri.parse(data[position].imageUrl))
    }


    class BeersViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.tv_name)
        val abv: TextView = view.findViewById(R.id.tv_abv)
        val beerImage: ImageView = view.findViewById(R.id.iv_beer_picture)
    }
}