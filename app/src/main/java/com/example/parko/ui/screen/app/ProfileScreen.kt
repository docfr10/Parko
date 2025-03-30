package com.example.parko.ui.screen.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.parko.utils.Routes
import com.example.parko.utils.TokenManager

@Composable
fun ProfileScreen(navController: NavHostController, tokenManager: TokenManager) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "This is profile screen")

            IconButton(onClick = {
                tokenManager.clearToken()
                navController.navigate(Routes.AUTHENTICATION_SCREEN) {
                    popUpTo(Routes.HOME_SCREEN) { inclusive = true }
                }
            }) {
                Image(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Exit to app"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    val tokenManager = TokenManager(LocalContext.current)

    ProfileScreen(navController = navController, tokenManager = tokenManager)
}