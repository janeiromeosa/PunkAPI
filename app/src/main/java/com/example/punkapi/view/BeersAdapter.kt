package com.example.punkapi.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.punkapi.BeersClickListener
import com.example.punkapi.R
import com.example.punkapi.model.BeersResponse
import com.squareup.picasso.Picasso
import java.util.*

class BeersAdapter(val data: MutableList<BeersResponse>, private val listener: BeersClickListener) :
    RecyclerView.Adapter<BeersAdapter.BeersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_beer, parent, false)
        return BeersViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BeersViewHolder, position: Int) {
        val beer = data[position]

        holder.bind(beer, listener)
    }


    class BeersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.tv_name)
        private val abv: TextView = itemView.findViewById(R.id.tv_abv)
        private val beerImage: ImageView = itemView.findViewById(R.id.img_beer)
        private val heart: ImageView = itemView.findViewById(R.id.iv_heart)


        fun bind(beer: BeersResponse, listener: BeersClickListener) {
            name.text = beer.name
            abv.text = beer.abv.toString()
            Picasso.get().load(beer.imageUrl).into(beerImage)
            heart.setImageResource(R.drawable.ic_favorite_black_24dp)

            itemView.setOnClickListener {
                listener.onBeerClicked(beer)
            }

        }
    }


}