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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun AppScreen() {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        topBar = {
            if (currentRoute !in listOf("SplashScreen", "RegistrationScreen"))
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    colors = TopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        scrolledContainerColor = MaterialTheme.colorScheme.background,
                        actionIconContentColor = MaterialTheme.colorScheme.onBackground,
                        navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
                        titleContentColor = MaterialTheme.colorScheme.onBackground,
                    ),
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(onClick = { navController.navigate(route = "ProfileScreen") }) {
                                Image(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "User photo"
                                )
                            }

                            Column {
                                Text(text = "Hello USERNAME!")
                                Text(text = "Park Your Car")
                            }

                            IconButton(onClick = { navController.navigate(route = "NotificationScreen") }) {
                                Image(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = "Notifications"
                                )
                            }
                        }
                    }
                )
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues = paddingValues),
            navController = navController,
            startDestination = "AppScreen"
        ) {
            navigation(startDestination = "SplashScreen", route = "AppScreen") {
                composable(route = "SplashScreen") {
                    SplashScreen(navController = navController)
                }
                composable(route = "RegistrationScreen") {
                    RegistrationScreen(navController = navController)
                }
                composable(route = "HomeScreen") {
                    HomeScreen()
                }
                composable(route = "ProfileScreen") {
                    ProfileScreen()
                }
                composable(route = "NotificationScreen") {
                    NotificationScreen()
                }
            }
        }
    }
}