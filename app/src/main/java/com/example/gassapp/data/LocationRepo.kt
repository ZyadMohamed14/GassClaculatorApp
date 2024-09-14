package com.example.gassapp.data

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import com.example.gassapp.utils.Constatns.Companion.EGYPT_LATITUDE
import com.example.gassapp.utils.Constatns.Companion.EGYPT_LONGITUDE
import com.example.gassapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class LocationRepo(context: Context?) : LocationService {
    private val geocoder = Geocoder(context!!)

    override suspend fun getAddresses(city: String): Flow<Resource<Location?>> =flow {
       try {
           emit(Resource.Loading())
            val addresses = geocoder.getFromLocationName(city ,1)
            Log.d("addresses", addresses.toString())
           if (addresses != null && !addresses.isEmpty()) {
               val address = addresses[0]
               val lat = address.latitude
               val lng = address.longitude
               if (isInEgypt(lat, lng)) {
                   emit(Resource.Error(Exception("Address is not in Egypt")))
                   return@flow
               }
               val location = Location("")
               location.latitude = lat
               location.longitude = lng
               emit(Resource.Success(location))
               return@flow
           }else{
               emit(Resource.Error(Exception("No addresses found")))
               return@flow
           }

        } catch (e: Exception) {
           emit(Resource.Error(Exception(e.message)))
           return@flow
        }

    }
    fun isInEgypt(latitude: Double, longitude: Double): Boolean {
        return latitude >= EGYPT_LATITUDE && longitude <= EGYPT_LONGITUDE
    }
}
