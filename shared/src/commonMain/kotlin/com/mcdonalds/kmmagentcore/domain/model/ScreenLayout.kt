package com.mcdonalds.kmmagentcore.domain.model

/**
 * Pure domain entity representing a fully resolved screen layout.
 * Free of any serialization or platform concerns so it can be consumed
 * directly by the native renderers (Jetpack Compose / SwiftUI).
 */
data class ScreenLayout(
    val screenId: String,
    val title: String,
    val components: List<ResolvedComponent>
)

