package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("content_section")
data class ContentSection(
    override val type: String,
    val layout: String? ? = null,
    val components: List<UIComponent>
) : UIComponent()

@Serializable
@SerialName("section")
data class Section(
    override val type: String,
    val layout: String ?= null ,
    val components: List<UIComponent>
) : UIComponent()


@Serializable
@SerialName("header_section")
data class HeaderSection(
    override val type: String,
    val components: List<UIComponent>
) : UIComponent()

@Serializable
@SerialName("footer_section")
data class FooterSection(
    override val type: String,
    val layout: String? = null ,
    val components: List<UIComponent>
) : UIComponent()
