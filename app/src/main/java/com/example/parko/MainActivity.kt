package com.example.parko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.parko.ui.screen.AppScreen
import com.example.parko.ui.theme.ParkoTheme
import com.example.parko.utils.TokenManager
import com.example.parko.viewmodel.AuthenticationViewModel
import com.example.parko.viewmodel.ParkingViewModel

class MainActivity : ComponentActivity() {
    private val authenticationViewModel: AuthenticationViewModel by viewModels()
    private val parkingViewModel: ParkingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ParkoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen(
                        authenticationViewModel = authenticationViewModel,
                        parkingViewModel = parkingViewModel,
                        tokenManager = TokenManager(context = LocalContext.current)
                    )
                }
            }
        }
    }
}