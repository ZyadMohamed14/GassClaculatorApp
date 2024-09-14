package com.example.gassapp

import android.Manifest
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.gassapp.adapter.CityAdapter
import com.example.gassapp.adapter.RouteAdapter
import com.example.gassapp.data.LocationRepo
import com.example.gassapp.databinding.ActivityMainBinding
import com.example.gassapp.domain.models.model.Route
import com.example.gassapp.domain.models.model.RoutePermutations
import com.example.gassapp.presentation.LocationViewModel
import com.example.gassapp.presentation.LocationViewModelFactory
import com.example.gassapp.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val progressDialog by lazy { ProgressDialog(this) }
    private val cities = mutableListOf<String>()
    private val defaultRouteList by lazy { ArrayList<String>() }
    private val startCities by lazy { ArrayList<String>() }
    private val allRoutes by lazy { ArrayList<Route>() }
    private val routeResults by lazy { ArrayList<Route>() }
    private val locationCache by lazy { mutableMapOf<String, Location>() }
    private val permutations by lazy { ArrayList<List<String>>() }
    private  lateinit var  cityAdapter :CityAdapter
    private lateinit var routeAdapter: RouteAdapter
    private lateinit var defaultRoute: Route
    private var bestRoute: Route? = null
    private var selectedRoute: Route? = null

    private var speed: Int = 0
    private var engineCC: Int = 0

    private lateinit var binding: ActivityMainBinding

    //    val locationViewModel: LocationViewModel by viewModels()
    private lateinit var locationViewModel: LocationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        requestPermissions()
        cityAdapter=CityAdapter(cities)
        val intent = intent
        speed = intent.getIntExtra("speed", 0)
        engineCC = intent.getIntExtra("cc", 0)
        initViewModel()
    }

    fun initViewModel() {
        val locationRepo = LocationRepo(this)  // Pass the context or use dependency injection
        val viewModelFactory = LocationViewModelFactory(locationRepo)
        locationViewModel =
            ViewModelProvider(this, viewModelFactory).get(LocationViewModel::class.java)
        lifecycleScope.launch {
            locationViewModel.locationState.collect { state ->
                when (state) {
                    is Resource.Error -> {
                        progressDialog.dismiss()
                        Toast.makeText(this@MainActivity, state.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }

                    is Resource.Loading -> {
                        progressDialog.setMessage("Getting Location")
                        progressDialog.show()
                    }

                    is Resource.Success -> {
                        progressDialog.dismiss()

                        locationCache[binding.cityEt.text.toString()] = state.data!!
                            cities.add(binding.cityEt.text.toString())
                            binding.cityEt.text.clear()
                            initCitiesView(cities)
                            Toast.makeText(this@MainActivity, "Location Added", Toast.LENGTH_SHORT)
                    }
                }
            }
        }
    }

    fun addCity(view: View) {
        val city = binding.cityEt.text.toString()
        if (city.isEmpty()) {
            Toast.makeText(this, "You should enter a district", Toast.LENGTH_SHORT).show()
            return
        }
        if (cities.contains(city)) {
            Toast.makeText(this, "This City Already Exists", Toast.LENGTH_SHORT).show()
            return
        }


        locationViewModel.getLocation(city)
    }
    fun initCitiesView(citiesList:MutableList<String>){
        cityAdapter.updateCities(citiesList)
        Log.d("zyad","cityAdapter${cityAdapter}")
        binding.rvCities.adapter = cityAdapter
    }


    private fun calculateTotalDistance(
        route: List<String>,
        locationCache: Map<String, Location>
    ): Double {
        var totalDistance = 0.0
        for (i in 0 until route.size - 1) {
            val cityLocation = locationCache[route[i]]
            val nextCityLocation = locationCache[route[i + 1]]
            if (cityLocation != null && nextCityLocation != null) {
                totalDistance += cityLocation.distanceTo(nextCityLocation).toDouble()
            }
        }

        return (totalDistance/1000) +10.0 // Convert to kilometers
    }

    private fun calculateFuelCost(
        distance: Double,
        fuelConsumptionPer100Km: Double,
        fuelPrice: Double
    ): Double {
        // Adjust the fuel consumption based on speed and engine CC
        val adjustedFuelConsumption =
            fuelConsumptionPer100Km * (1 + (speed / 100) * 0.1) * (1 + (engineCC / 1000) * 0.05)

        return (distance / 100) * adjustedFuelConsumption * fuelPrice
    }

    private fun calculateTime(distance: Double, speed: Int): Double {
        return (distance / speed)
    }

    fun requestPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1
            )
        }
    }

    fun gassCalculation(view: View) {
        if (cities.isEmpty() || cities.size == 1) {
            Toast.makeText(this, "Enter Cities", Toast.LENGTH_SHORT).show()
            return
        }

        // Clear previous data
        defaultRouteList.clear()
        startCities.clear()
        permutations.clear()
        routeResults.clear()
        allRoutes.clear()

        defaultRouteList.addAll(cities)
        startCities.addAll(cities)
        progressDialog.setMessage("Calculating...")
        progressDialog.setCancelable(false)
        progressDialog.show()
    Log.d("zyad", "cach: $locationCache")
        // Launch a coroutine in the IO dispatcher for background processing
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Calculate the default route distance and cost
                val defaultRouteDistance = calculateTotalDistance(defaultRouteList, locationCache)
                val fuelConsumptionPer100Km = 8.5
                val currentFuelPrice = 13.0
                val defaultRouteFuelCost = calculateFuelCost(
                    defaultRouteDistance,
                    fuelConsumptionPer100Km,
                    currentFuelPrice
                )
                val defaultRouteTime = calculateTime(defaultRouteDistance, speed)
                defaultRoute = Route(
                    defaultRouteList,
                    defaultRouteDistance,
                    defaultRouteFuelCost,
                    defaultRouteTime
                )
                routeResults.add(defaultRoute)

                if (cities.size > 2) {
                    // Calculate all permutations and find the best route starting from the first city
                    for (startCity in startCities) {
                        permutations.addAll(RoutePermutations.generateRoutes(cities, startCity))
                        for (route in permutations) {
                            val distance = calculateTotalDistance(route, locationCache)
                            allRoutes.add(Route(distance, route))
                        }
                    }

                    // Sort all routes by distance
                    allRoutes.sortBy { it.distance }

                    // Find the best route starting from the specified start city
                    bestRoute = allRoutes.firstOrNull { it.cities[0] == cities[0] }

                    bestRoute?.let {
                        if (it.cities != defaultRouteList) {
                            val bestRouteTime = calculateTime(it.distance, speed)
                            val bestRouteFuelCost = calculateFuelCost(
                                it.distance,
                                fuelConsumptionPer100Km,
                                currentFuelPrice
                            )
                            it.fuelCost = bestRouteFuelCost
                            it.time = bestRouteTime
                            it.isBestRoute = true
                        } else {
                            it.isBestRoute = false
                            defaultRoute.isBestRoute = true
                        }
                        routeResults.add(it)
                    }
                }

                withContext(Dispatchers.Main) {
                    progressDialog.dismiss()
                    routeAdapter = RouteAdapter(routeResults) { route ->
                        showRouteSelectionDialog(route)
                    }
                    binding.rvRouht.adapter = routeAdapter

                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    progressDialog.dismiss()
                    Toast.makeText(
                        applicationContext,
                        "Error during calculation",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun showRouteSelectionDialog(route: Route) {
        AlertDialog.Builder(this)
            .setTitle("Select Route")
            .setMessage("Do you want to select this route?")
            .setPositiveButton("OK") { dialog, _ ->
                // Handle route selection
                selectedRoute = route
                Toast.makeText(this, "Route selected", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}