package com.example.parko.viewmodel

import androidx.lifecycle.ViewModel
import com.example.parko.model.requests.LoginRequest
import com.example.parko.model.requests.UserRequest
import com.example.parko.model.response.BaseResponse
import com.example.parko.network.ApiService
import com.example.parko.utils.TokenManager

class AuthenticationViewModel() : ViewModel() {

    suspend fun signUp(userRequest: UserRequest): BaseResponse =
        ApiService.signUp(userRequest = userRequest)

    suspend fun logIn(tokenManager: TokenManager, loginRequest: LoginRequest): Boolean {
        val response = ApiService.logIn(loginRequest = loginRequest)

        return if (response.success) {
            tokenManager.saveToken(response.message)
            true
        } else false
    }
}