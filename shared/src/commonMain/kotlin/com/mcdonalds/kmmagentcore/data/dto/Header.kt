package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
@SerialName("header")
data class Header(
    override val type: String,
    val props: HeaderProps
) : UIComponent()

@Serializable
data class HeaderProps(
    val title: String,
    val actions: List<IconAction>
)