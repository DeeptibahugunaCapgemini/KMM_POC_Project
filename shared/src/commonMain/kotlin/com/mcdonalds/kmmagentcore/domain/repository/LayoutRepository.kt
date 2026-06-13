package com.mcdonalds.kmmagentcore.domain.repository

import com.mcdonalds.kmmagentcore.domain.model.ScreenLayout

/**
 * Domain contract for obtaining screen layouts. The implementation lives
 * in the data layer, keeping the domain free of framework dependencies.
 */
interface LayoutRepository {
    suspend fun getScreenLayout(screenId: String): ScreenLayout
}

