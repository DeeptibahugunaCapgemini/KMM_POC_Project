package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("content_section")
data class ContentSection(
    override val type: String,
    val components: List<SDUIComponent>
) : SDUIComponent()

@Serializable
@SerialName("section")
data class Section(
    override val type: String,
    val components: List<SDUIComponent>
) : SDUIComponent()


@Serializable
@SerialName("footer_section")
data class FooterSection(
    override val type: String,
    val components: List<SDUIComponent>
) : SDUIComponent()
