package com.mcdonalds.kmmagentcore.domain.usecase

import com.mcdonalds.kmmagentcore.data.dto.OrderAgentResponse
import com.mcdonalds.kmmagentcore.domain.repository.LayoutRepository

/**
 * Single business action: fetch and resolve the layout for a given screen.
 */
class GetScreenLayoutUseCase(
    private val repository: LayoutRepository
) {
    suspend operator fun invoke(userId :String , input :String): OrderAgentResponse =
        repository.getScreenLayout( userId,input)
}

