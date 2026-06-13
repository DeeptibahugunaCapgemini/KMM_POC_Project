package com.mcdonalds.kmmagentcore.di

import com.mcdonalds.kmmagentcore.data.datasource.DummyLayoutDataSource
import com.mcdonalds.kmmagentcore.data.datasource.LayoutRemoteDataSource
import com.mcdonalds.kmmagentcore.data.repository.LayoutRepositoryImpl
import com.mcdonalds.kmmagentcore.domain.repository.LayoutRepository
import com.mcdonalds.kmmagentcore.domain.usecase.GetScreenLayoutUseCase
import com.mcdonalds.kmmagentcore.presentation.AgentOrchestrator

/**
 * Lightweight composition root that wires the clean-architecture graph.
 * Swap [DummyLayoutDataSource] for a real network source when the
 * backend is ready, without touching any other layer.
 */
object SharedModule {

    private val dataSource: LayoutRemoteDataSource by lazy { DummyLayoutDataSource() }

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

