package com.example.parko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parko.network.KtorClient
import com.example.parko.ui.screen.AppScreen
import com.example.parko.ui.theme.ParkoTheme
import com.example.parko.viewmodel.AuthenticationViewModel
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    private val authenticationViewModel: AuthenticationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ParkoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen(authenticationViewModel = authenticationViewModel)
                }
            }
        }
    }
}


suspend fun getAllParks(): List<Parking> {
    // Укажите корректный IP-адрес/домен сервера и порт
    val url = "http://10.0.2.2:8080/api/v1/get-all-parks"
    return KtorClient.client.get(url).body()
}


class ParkingViewModel : ViewModel() {
    val parks = mutableStateOf<List<Parking>>(emptyList())
    val errorMessage = mutableStateOf("")

    init {
        fetchParks()
    }

    private fun fetchParks() {
        viewModelScope.launch {
            try {
                parks.value = getAllParks()
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage ?: "Ошибка получения данных"
            }
        }
    }
}

@Serializable
data class Parking(
    val id: Int,
    val name: String,
    val address: String,
    val description: String,
    val parkingLots: Int
)