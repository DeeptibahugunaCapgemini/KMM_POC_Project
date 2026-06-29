package com.mcdonalds.kmmagentcore.presentation

import com.mcdonalds.kmmagentcore.data.dto.SDUIComponent

/**
 * UI-agnostic state the native renderers observe. Compose/SwiftUI simply
 * react to these states; all decisions are made in shared code.
 */
sealed interface ScreenState {

    data object Loading : ScreenState

    data class Ready(
        val screenId: String,
        val components: List<SDUIComponent>,
        val isLoading: Boolean = false,
        val error: String? = null
    ): ScreenState

    data class Error(
        val message: String
    ) : ScreenState
}
