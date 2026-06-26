package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("message")
data class MessageComponent(
    val label : String?=null,
    override val type: String,
) : SDUIComponent()
