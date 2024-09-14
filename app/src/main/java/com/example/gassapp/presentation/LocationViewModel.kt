package com.example.gassapp.presentation

import android.location.Address
import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gassapp.data.LocationRepo
import com.example.gassapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class LocationViewModel(private val locationRepo: LocationRepo) : ViewModel() {

    private val _locationState = MutableSharedFlow<Resource<Location?>>()
    val locationState: SharedFlow<Resource<Location?>> = _locationState

    fun getLocation(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            locationRepo.getAddresses(city).collect { result ->
              _locationState.emit(result)
            }
        }
    }
}