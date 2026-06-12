package com.example.kotlinproject.domain.model

/**
 * A UI component whose design tokens have already been resolved into
 * concrete values (color hex, spacing in dp, font size, etc.).
 */
data class ResolvedComponent(
    val id: String,
    val type: ComponentType,
    val text: String?,
    val colorHex: String?,
    val spacing: Int,
    val fontSize: Int?,
    val fontWeight: String?,
    val action: ResolvedAction?,
    val children: List<ResolvedComponent>
)

