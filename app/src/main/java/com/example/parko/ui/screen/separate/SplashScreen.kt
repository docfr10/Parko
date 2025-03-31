package com.example.parko.ui.screen.separate

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.parko.utils.Routes
import com.example.parko.utils.TokenManager
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, tokenManager: TokenManager) {
    val startAnimation = remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation.value) 1f else 0f,
        animationSpec = tween(durationMillis = 3000)
    )

    LaunchedEffect(Unit) {
        startAnimation.value = true
        delay(timeMillis = 4000)
        if (!tokenManager.getToken().isNullOrEmpty())
            navController.navigate(Routes.HOME_SCREEN) {
                popUpTo(Routes.SPLASH_SCREEN) { inclusive = true }
            }
        else
            navController.navigate(Routes.AUTHENTICATION_SCREEN) {
                popUpTo(Routes.SPLASH_SCREEN) { inclusive = true }
            }
    }

    SplashScreenAnimation(alphaAnim = alphaAnim.value)
}

@Composable
fun SplashScreenAnimation(alphaAnim: Float) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(200.dp)
                .alpha(alpha = alphaAnim),
            imageVector = Icons.Default.LocalParking,
            contentDescription = "Logo icon",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}
