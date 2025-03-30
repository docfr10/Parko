package com.example.parko.model.requests

import kotlinx.serialization.Serializable

@Serializable
data class GetParkingRequest(
    val id: Int,
    val name: String,
    val address: String,
    val description: String,
    val parkingLots: Int
)