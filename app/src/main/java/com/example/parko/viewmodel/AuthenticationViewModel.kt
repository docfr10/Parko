package com.example.parko.viewmodel

import androidx.lifecycle.ViewModel
import com.example.parko.model.requests.LoginRequest
import com.example.parko.model.requests.UserRequest
import com.example.parko.model.response.BaseResponse
import com.example.parko.network.KtorClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthenticationViewModel : ViewModel() {
    suspend fun signUp(userRequest: UserRequest): BaseResponse {
        val response = KtorClient.client.post("http://10.0.2.2:8080/api/v1/signup") {
            contentType(ContentType.Application.Json)
            setBody(userRequest)
        }

        return response.body<BaseResponse>()
    }

    suspend fun logIn(loginRequest: LoginRequest): BaseResponse {
        val response = KtorClient.client.post("http://10.0.2.2:8080/api/v1/login") {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }

        return response.body<BaseResponse>()
    }
}