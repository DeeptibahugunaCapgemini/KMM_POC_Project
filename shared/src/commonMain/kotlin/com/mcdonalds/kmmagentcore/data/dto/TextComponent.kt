package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("text")
data class TextComponent(
    override val type: String,
    val props: TextProps
) : UIComponent()

@Serializable
data class TextProps(
    val value: String,
    val style: String?
)