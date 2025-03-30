package com.example.parko.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.parko.ui.screen.app.HomeScreen
import com.example.parko.ui.screen.app.NotificationScreen
import com.example.parko.ui.screen.app.ProfileScreen
import com.example.parko.ui.screen.separate.AuthenticationScreen
import com.example.parko.ui.screen.separate.RegistrationScreen
import com.example.parko.ui.screen.separate.SplashScreen
import com.example.parko.utils.Routes
import com.example.parko.viewmodel.AuthenticationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(authenticationViewModel: AuthenticationViewModel) {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp), topBar = {
            if (currentRoute !in listOf(
                    Routes.SPLASH_SCREEN,
                    Routes.AUTHENTICATION_SCREEN,
                    Routes.REGISTRATION_SCREEN
                )
            ) TopAppBar(
                modifier = Modifier.fillMaxWidth(), colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background,
                    actionIconContentColor = MaterialTheme.colorScheme.onBackground,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                ), title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = { navController.navigate(route = Routes.PROFILE_SCREEN) }) {
                            Image(
                                imageVector = Icons.Default.Person,
                                contentDescription = "User photo"
                            )
                        }

                        Column {
                            Text(text = "Hello USERNAME!")
                            Text(text = "Park Your Car")
                        }

                        IconButton(onClick = { navController.navigate(route = Routes.NOTIFICATION_SCREEN) }) {
                            Image(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notifications"
                            )
                        }
                    }
                })
        }) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues = paddingValues),
            navController = navController,
            startDestination = Routes.SEPARATED_SCREENS
        ) {
            navigation(startDestination = Routes.SPLASH_SCREEN, route = Routes.SEPARATED_SCREENS) {
                composable(route = Routes.SPLASH_SCREEN) {
                    SplashScreen(navController = navController)
                }
                composable(route = Routes.AUTHENTICATION_SCREEN) {
                    AuthenticationScreen(
                        authenticationViewModel = authenticationViewModel,
                        navController = navController
                    )
                }
                composable(route = Routes.REGISTRATION_SCREEN) {
                    RegistrationScreen(
                        authenticationViewModel = authenticationViewModel,
                        navController = navController
                    )
                }
            }
            navigation(startDestination = Routes.HOME_SCREEN, route = Routes.APP_SCREENS) {
                composable(route = Routes.HOME_SCREEN) {
                    HomeScreen()
                }
                composable(route = Routes.PROFILE_SCREEN) {
                    ProfileScreen()
                }
                composable(route = Routes.NOTIFICATION_SCREEN) {
                    NotificationScreen()
                }
            }
        }
    }
}