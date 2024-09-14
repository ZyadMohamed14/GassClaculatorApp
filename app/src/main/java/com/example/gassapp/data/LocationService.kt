package com.example.gassapp.data

import android.location.Address
import android.location.Location
import com.example.gassapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LocationService {
    suspend fun getAddresses(city: String): Flow<Resource<Location?>>
}