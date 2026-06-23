package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ButtonAction(
    val type: String,
    val label: String,
    val action: String,
    val payload: Map<String, String>? = null
)