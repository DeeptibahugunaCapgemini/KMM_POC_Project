package com.example.kotlinproject.data.repository

import com.example.kotlinproject.data.datasource.LayoutRemoteDataSource
import com.example.kotlinproject.data.dto.LayoutSchemaDto
import com.example.kotlinproject.data.mapper.LayoutMapper
import com.example.kotlinproject.domain.model.ScreenLayout
import com.example.kotlinproject.domain.repository.LayoutRepository
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

