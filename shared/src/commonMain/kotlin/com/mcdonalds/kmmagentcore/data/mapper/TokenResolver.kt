package com.mcdonalds.kmmagentcore.data.mapper

import com.mcdonalds.kmmagentcore.data.dto.DesignTokensDto
import com.mcdonalds.kmmagentcore.data.dto.TypographyTokenDto

/**
 * Resolves design-token references (e.g. "primary", "medium") into the
 * concrete values declared in the schema's token table.
 */
class TokenResolver(private val tokens: DesignTokensDto) {

    fun resolveColor(token: String?): String? = token?.let { tokens.colors[it] }

    fun resolveSpacing(token: String?): Int = token?.let { tokens.spacing[it] } ?: 0

    fun resolveTypography(token: String?): TypographyTokenDto? =
        token?.let { tokens.typography[it] }
}

