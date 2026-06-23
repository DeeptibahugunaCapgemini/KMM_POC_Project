package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("chip")
data class ChipComponent(
    override val type: String,
    val props: ChipProps
) : UIComponent()

@Serializable
data class ChipProps(
    val label: String,
    val icon: String? = null,
    val action: String? = null,
    val payload: Map<String, String>? = null
)