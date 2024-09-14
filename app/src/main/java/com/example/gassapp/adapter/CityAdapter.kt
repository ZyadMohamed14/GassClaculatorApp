package com.example.gassapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gassapp.R

class CityAdapter(
    private val cities: MutableList<String>,
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    inner class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cityTextView: TextView = view.findViewById(R.id.citytv)
        val removeButton: ImageButton = view.findViewById(R.id.removeBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_item_layout, parent, false)
        return CityViewHolder(view)
    }
    fun updateCities(newCities: List<String>) {
        val diffCallback = CityDiffCallback(cities, newCities)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

//        cities.clear()
//        cities.addAll(newCities)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.cityTextView.text = city

        holder.removeButton.setOnClickListener {
            removeCity(position)
        }
    }

    override fun getItemCount(): Int = cities.size

    fun removeCity(position: Int) {
        if (position >= -1 && position < cities.size) {
            cities.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}