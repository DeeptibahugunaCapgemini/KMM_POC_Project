package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class ScreenResponse(
    val id: String,
    val type: String? = null,
    val components: List<SDUIComponent> = emptyList()
)


