package com.example.parko.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parko.model.requests.GetParkingRequest
import com.example.parko.network.KtorClient
import com.example.parko.utils.TokenManager
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import kotlinx.coroutines.launch

class ParkingViewModel : ViewModel() {
    val parks = mutableStateOf<List<GetParkingRequest>>(emptyList())
    val errorMessage = mutableStateOf("")

    suspend fun getAllParks(token: String): List<GetParkingRequest> {
        val url = "http://10.0.2.2:8080/api/v1/get-all-parks"
        return KtorClient.client.get(url) {
            header("Authorization", "Bearer $token")
        }.body()
    }

    fun fetchParks(tokenManager: TokenManager) {
        viewModelScope.launch {
            try {
                parks.value = getAllParks(tokenManager.getToken() ?: "")
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage ?: "Ошибка получения данных"
            }
        }
    }
}