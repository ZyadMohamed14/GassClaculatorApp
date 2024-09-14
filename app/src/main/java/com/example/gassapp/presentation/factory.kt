package com.example.gassapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gassapp.data.LocationRepo

class LocationViewModelFactory(private val repo: LocationRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LocationViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}