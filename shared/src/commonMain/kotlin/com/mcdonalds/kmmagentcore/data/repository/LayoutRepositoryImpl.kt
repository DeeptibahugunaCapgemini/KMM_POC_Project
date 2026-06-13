package com.mcdonalds.kmmagentcore.data.repository

import com.mcdonalds.kmmagentcore.data.datasource.LayoutRemoteDataSource
import com.mcdonalds.kmmagentcore.data.dto.LayoutSchemaDto
import com.mcdonalds.kmmagentcore.data.mapper.LayoutMapper
import com.mcdonalds.kmmagentcore.domain.model.ScreenLayout
import com.mcdonalds.kmmagentcore.domain.repository.LayoutRepository
import kotlinx.serialization.json.Json

/**
 * Concrete repository: fetches raw JSON, parses the tokens, and maps the
 * schema into resolved domain entities.
 */
class LayoutRepositoryImpl(
    private val dataSource: LayoutRemoteDataSource,
    private val mapper: LayoutMapper = LayoutMapper(),
    private val json: Json = Json { ignoreUnknownKeys = true }
) : LayoutRepository {

    override suspend fun getScreenLayout(screenId: String): ScreenLayout {
        val rawJson = dataSource.fetchLayoutJson(screenId)
        val dto = json.decodeFromString<LayoutSchemaDto>(rawJson)
        return mapper.toDomain(dto)
    }
}

