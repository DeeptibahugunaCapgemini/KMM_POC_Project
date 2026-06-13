package com.mcdonalds.kmmagentcore.presentation

import com.mcdonalds.kmmagentcore.domain.model.ResolvedComponent

/**
 * UI-agnostic state the native renderers observe. Compose/SwiftUI simply
 * react to these states; all decisions are made in shared code.
 */
sealed interface ScreenState {
    data object Loading : ScreenState
    data class Ready(
        val title: String,
        val components: List<ResolvedComponent>
    ) : ScreenState
    data class Error(val message: String) : ScreenState
}

