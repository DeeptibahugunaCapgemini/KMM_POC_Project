package com.example.kotlinproject.data.mapper

import com.example.kotlinproject.data.dto.LayoutSchemaDto
import com.example.kotlinproject.data.dto.UiComponentDto
import com.example.kotlinproject.domain.model.ComponentType
import com.example.kotlinproject.domain.model.ResolvedAction
import com.example.kotlinproject.domain.model.ResolvedComponent
import com.example.kotlinproject.domain.model.ScreenLayout

/**
 * Converts wire-format DTOs into domain entities, resolving every design
 * token to a concrete value along the way (the layout schema resolution step).
 */
class LayoutMapper {

    fun toDomain(dto: LayoutSchemaDto): ScreenLayout {
        val resolver = TokenResolver(dto.tokens)
        return ScreenLayout(
            screenId = dto.screenId,
            title = dto.title,
            components = dto.components.map { it.toDomain(resolver) }
        )
    }

    private fun UiComponentDto.toDomain(resolver: TokenResolver): ResolvedComponent {
        val typography = resolver.resolveTypography(typographyToken)
        return ResolvedComponent(
            id = id,
            type = type.toComponentType(),
            text = text,
            colorHex = resolver.resolveColor(colorToken),
            spacing = resolver.resolveSpacing(spacingToken),
            fontSize = typography?.fontSize,
            fontWeight = typography?.fontWeight,
            action = action?.let { ResolvedAction(it.type, it.payload) },
            children = children.map { it.toDomain(resolver) }
        )
    }

    private fun String.toComponentType(): ComponentType =
        when (lowercase()) {
            "column" -> ComponentType.COLUMN
            "row" -> ComponentType.ROW
            "text" -> ComponentType.TEXT
            "button" -> ComponentType.BUTTON
            "image" -> ComponentType.IMAGE
            "spacer" -> ComponentType.SPACER
            else -> ComponentType.UNKNOWN
        }
}

