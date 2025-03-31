package com.example.parko.network

import com.example.parko.model.requests.GetParkingRequest
import com.example.parko.model.requests.LoginRequest
import com.example.parko.model.requests.UserRequest
import com.example.parko.model.response.BaseResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

object ApiService {
    private const val BASE_URL = "http://10.0.2.2:8080/api/v1/"

    suspend fun signUp(userRequest: UserRequest): BaseResponse {
        return KtorClient.client.post("${BASE_URL}signup") {
            contentType(ContentType.Application.Json)
            setBody(userRequest)
        }.body()
    }

    suspend fun logIn(loginRequest: LoginRequest): BaseResponse {
        return KtorClient.client.post("${BASE_URL}login") {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }.body()
    }

    suspend fun getAllParks(token: String): List<GetParkingRequest> {
        return KtorClient.client.get("${BASE_URL}get-all-parks") {
            header("Authorization", "Bearer $token")
        }.body()
    }
}
