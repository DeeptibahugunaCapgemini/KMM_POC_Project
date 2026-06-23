package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("input_bar")
data class InputBar(
    override val type: String,
    val props: InputBarProps
) : UIComponent()

@Serializable
data class InputBarProps(
    val placeholder: String,
    val actions: List<ButtonAction>
)
