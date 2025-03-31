package com.example.parko.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parko.model.requests.GetParkingRequest
import com.example.parko.network.ApiService
import com.example.parko.utils.TokenManager
import kotlinx.coroutines.launch

class ParkingViewModel() : ViewModel() {
    val parks = mutableStateOf<List<GetParkingRequest>>(emptyList())
    val errorMessage = mutableStateOf("")

    fun getAllParks(tokenManager: TokenManager) {
        viewModelScope.launch {
            try {
                parks.value = ApiService.getAllParks(tokenManager.getToken() ?: "")
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage ?: "Ошибка получения данных"
            }
        }
    }
}
