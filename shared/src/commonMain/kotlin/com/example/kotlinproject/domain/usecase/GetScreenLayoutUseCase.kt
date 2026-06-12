package com.example.kotlinproject.domain.usecase

import com.example.kotlinproject.domain.model.ScreenLayout
import com.example.kotlinproject.domain.repository.LayoutRepository

/**
 * Single business action: fetch and resolve the layout for a given screen.
 */
class GetScreenLayoutUseCase(
    private val repository: LayoutRepository
) {
    suspend operator fun invoke(screenId: String): ScreenLayout =
        repository.getScreenLayout(screenId)
}

