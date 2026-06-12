package com.example.kotlinproject.di

import com.example.kotlinproject.data.datasource.DummyLayoutDataSource
import com.example.kotlinproject.data.datasource.LayoutRemoteDataSource
import com.example.kotlinproject.data.repository.LayoutRepositoryImpl
import com.example.kotlinproject.domain.repository.LayoutRepository
import com.example.kotlinproject.domain.usecase.GetScreenLayoutUseCase
import com.example.kotlinproject.presentation.AgentOrchestrator

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

