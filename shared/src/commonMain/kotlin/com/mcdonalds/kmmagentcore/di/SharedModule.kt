package com.mcdonalds.kmmagentcore.di

import com.mcdonalds.kmmagentcore.data.datasource.DummyLayoutDataSource
import com.mcdonalds.kmmagentcore.data.datasource.KtorLayoutDataSource
import com.mcdonalds.kmmagentcore.data.datasource.LayoutRemoteDataSource
import com.mcdonalds.kmmagentcore.data.remote.createHttpClient
import com.mcdonalds.kmmagentcore.data.repository.LayoutRepositoryImpl
import com.mcdonalds.kmmagentcore.domain.repository.LayoutRepository
import com.mcdonalds.kmmagentcore.domain.usecase.GetScreenLayoutUseCase
import com.mcdonalds.kmmagentcore.presentation.AgentOrchestrator
import io.ktor.client.HttpClient

//import io.ktor.client.HttpClient

/**
 * Lightweight composition root that wires the clean-architecture graph.
 * The remote data source now fetches layout JSON from the backend over
 * HTTP via Ktor. Point [baseUrl] at your server before creating the first
 * orchestrator, or swap [KtorLayoutDataSource] for `DummyLayoutDataSource`
 * to develop fully offline — no other layer needs to change.
 */
object SharedModule {

    /** Backend root for the layout API. Override before first use if needed. */
    var baseUrl: String = KtorLayoutDataSource.DEFAULT_BASE_URL

    private val httpClient: HttpClient by lazy { createHttpClient() }

    private val dataSource: LayoutRemoteDataSource by lazy {
        KtorLayoutDataSource(httpClient, baseUrl)
    }
 //   private val dataSource: LayoutRemoteDataSource by lazy { DummyLayoutDataSource() }


    private val repository: LayoutRepository by lazy {
        LayoutRepositoryImpl(dataSource)
    }

    private val getScreenLayoutUseCase: GetScreenLayoutUseCase by lazy {
        GetScreenLayoutUseCase(repository)
    }

    /** Native callers create one orchestrator per screen/view-model. */
    fun provideOrchestrator(): AgentOrchestrator =
        AgentOrchestrator(getScreenLayoutUseCase)
}

