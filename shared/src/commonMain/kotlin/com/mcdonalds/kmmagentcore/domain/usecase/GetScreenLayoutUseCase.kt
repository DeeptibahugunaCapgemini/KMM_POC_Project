package com.mcdonalds.kmmagentcore.domain.usecase

import com.mcdonalds.kmmagentcore.data.dto.Screen
import com.mcdonalds.kmmagentcore.domain.repository.LayoutRepository

/**
 * Single business action: fetch and resolve the layout for a given screen.
 */
class GetScreenLayoutUseCase(
    private val repository: LayoutRepository
) {
    suspend operator fun invoke(screenId: String): Screen =
        repository.getScreenLayout(screenId)
}

