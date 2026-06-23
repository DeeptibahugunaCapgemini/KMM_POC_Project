package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Screen(
    val screen: PageComponent
)

@Serializable
data class PageComponent(
    val id: String,
    val type: String,
    val layout: String?,
    val components: List<UIComponent> = emptyList()
)

