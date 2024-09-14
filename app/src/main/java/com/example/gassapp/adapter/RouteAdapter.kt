package com.example.gassapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gassapp.R
import com.example.gassapp.domain.models.model.Route

class RouteAdapter(

    private var routes: ArrayList<Route>,
    private val onRouteSelected: (Route) -> Unit
) : RecyclerView.Adapter<RouteAdapter.RouteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_route, parent, false)
        return RouteViewHolder(view)
    }

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        val route = routes[position]
        holder.bind(route)
    }


    override fun getItemCount(): Int = routes.size

    inner class RouteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val routeNameTextView: TextView = itemView.findViewById(R.id.routeNameTextView)
        private val routeCitiesTextView: TextView = itemView.findViewById(R.id.routeCitiesTextView)
        private val routeDistanceTextView: TextView =
            itemView.findViewById(R.id.routeDistanceTextView)
        private val routeFuelCostTextView: TextView =
            itemView.findViewById(R.id.routeFuelCostTextView)
        private val routeTimeTextView: TextView = itemView.findViewById(R.id.routeTimeTextView)
        private val bestTv: TextView = itemView.findViewById(R.id.bestTv)

        @SuppressLint("SetTextI18n")
        fun bind(route: Route) {
            routeNameTextView.text = "Route Details"
            routeCitiesTextView.text = route.cities.joinToString(", ")

            // Handle null values
            routeDistanceTextView.text = route.distance?.let { "${String.format("%.2f", it)} km" } ?: "N/A"
            routeFuelCostTextView.text = route.fuelCost?.let { "${String.format("%.2f", it)} EGP" } ?: "N/A"
            formatTime(route.time ?: 0.0, routeTimeTextView)

            if (route.isBestRoute) {
                bestTv.text = "Best Route"
                bestTv.visibility = View.VISIBLE
            } else {
                bestTv.visibility = View.GONE
            }

            itemView.setOnClickListener {
                onRouteSelected(route)
            }
        }

        private fun formatTime(timeInHours: Double, textView: TextView) {
            val hours = timeInHours.toInt()
            val minutes = ((timeInHours - hours) * 60).toInt()

            if (hours > 0) {
                textView.text = String.format(
                    "%d hour%s and %d minute%s", hours,
                    if (hours == 1) "" else "s", minutes,
                    if (minutes == 1) "" else "s"
                )
            } else {
                textView.text = String.format("%d minute%s", minutes, if (minutes == 1) "" else "s")
            }
        }
    }

}