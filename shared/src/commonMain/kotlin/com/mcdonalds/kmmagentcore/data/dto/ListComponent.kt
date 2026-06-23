package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("list")
data class ListComponent(
    override val type: String,
    val props: ListProps
) : UIComponent()

@Serializable
data class ListProps(
    val items: List<UIComponent>
)