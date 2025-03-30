package com.example.parko.ui.screen.separate

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.parko.model.requests.LoginRequest
import com.example.parko.viewmodel.AuthenticationViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AuthenticationScreen(
    navController: NavHostController,
    authenticationViewModel: AuthenticationViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = BringIntoViewRequester()

    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .onFocusEvent {
                if (it.isFocused)
                    coroutineScope.launch { bringIntoViewRequester.bringIntoView() }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "authentication",
            tint = MaterialTheme.colorScheme.surfaceTint
        )
        // Text to Display the current Screen
        Text(
            text = "Authentication",
            color = MaterialTheme.colorScheme.onSurface
        )
        // OutlinedTextField to type the Email
        OutlinedTextField(
            value = email.value,
            textStyle = MaterialTheme.typography.bodyMedium,
            onValueChange = { email.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp)
                .background(MaterialTheme.colorScheme.background),
            label = { Text(text = "Email") },
            placeholder = {
                Text(
                    text = "example@domain.com",
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        // OutlinedTextField to type the password
        OutlinedTextField(
            value = password.value,
            textStyle = MaterialTheme.typography.bodyMedium,
            onValueChange = { password.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp)
                .background(MaterialTheme.colorScheme.background),
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        // SignIn button
        Button(
            onClick = {
                coroutineScope.launch {
                    if (authenticationViewModel.logIn(
                            LoginRequest(
                                email = email.value,
                                password = password.value,
                                isActivate = true
                            )
                        ).success
                    ) navController.navigate("HomeScreen") {
                        popUpTo("AuthenticationScreen") { inclusive = true }
                    }
                }
            },
            modifier = Modifier
                .padding(top = 10.dp, start = 5.dp, end = 5.dp)
                .fillMaxWidth()
                .bringIntoViewRequester(bringIntoViewRequester),
            shape = MaterialTheme.shapes.extraLarge
        ) { Text(text = "Sign in") }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        // Registration button
        Button(
            onClick = { navController.navigate("RegistrationScreen") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge
        ) { Text(text = "Registered") }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthenticationScreenPreview() {
    val navController = rememberNavController()
    val authenticationViewModel: AuthenticationViewModel = viewModel()

    AuthenticationScreen(
        navController = navController,
        authenticationViewModel = authenticationViewModel
    )
}