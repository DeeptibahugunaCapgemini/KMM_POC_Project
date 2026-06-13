package com.mcdonalds.kmmagentcore.presentation

import com.mcdonalds.kmmagentcore.domain.usecase.GetScreenLayoutUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Centralized agent orchestration. Drives the use case, owns the screen
 * state, and exposes it as an observable [StateFlow] that both Jetpack
 * Compose and SwiftUI can collect, guaranteeing consistent behavior across channels.
 */
class AgentOrchestrator(
    private val getScreenLayout: GetScreenLayoutUseCase,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
) {
    private val _state = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val state: StateFlow<ScreenState> = _state.asStateFlow()

    /** Fire-and-forget entry point convenient for native callers. */
    fun load(screenId: String) {
        scope.launch { loadScreen(screenId) }
    }

    /** Suspend variant for callers that already have a coroutine context. */
    suspend fun loadScreen(screenId: String) {
        _state.value = ScreenState.Loading
        _state.value = try {
            val layout = getScreenLayout(screenId)
            ScreenState.Ready(layout.title, layout.components)
        } catch (e: Exception) {
            ScreenState.Error(e.message ?: "Failed to load layout")
        }
    }
}

