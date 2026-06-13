package com.mcdonalds.kmmagentcore.data.datasource

/**
 * Abstraction over wherever the raw layout JSON comes from
 * (network, cache, local bundle, etc.).
 */
interface LayoutRemoteDataSource {
    suspend fun fetchLayoutJson(screenId: String): String
}

