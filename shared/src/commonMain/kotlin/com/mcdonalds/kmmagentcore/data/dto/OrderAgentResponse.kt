package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class OrderAgentResponse(
    val username: String,
    val usertype: String,
    val userEarnedPoints: String,
    val clv: String,
    val daypart: String,
    val screen: ScreenResponse
)
