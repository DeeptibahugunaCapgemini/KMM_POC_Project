package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Wire-format DTOs that mirror the layout JSON schema produced by the
 * agent backend. These stay in the data layer; the domain never sees them.
 */
@Serializable
data class LayoutSchemaDto(
    val version: String,
    val screenId: String,
    val title: String,
    val tokens: DesignTokensDto,
    val components: List<UiComponentDto>
)

@Serializable
data class DesignTokensDto(
    val colors: Map<String, String> = emptyMap(),
    val spacing: Map<String, Int> = emptyMap(),
    val typography: Map<String, TypographyTokenDto> = emptyMap()
)

@Serializable
data class TypographyTokenDto(
    val fontSize: Int,
    val fontWeight: String = "normal"
)

@Serializable
data class UiComponentDto(
    val id: String,
    val type: String,
    val text: String? = null,
    @SerialName("colorToken") val colorToken: String? = null,
    @SerialName("spacingToken") val spacingToken: String? = null,
    @SerialName("typographyToken") val typographyToken: String? = null,
    val action: ComponentActionDto? = null,
    val children: List<UiComponentDto> = emptyList()
)

@Serializable
data class ComponentActionDto(
    val type: String,
    val payload: Map<String, String> = emptyMap()
)

