package com.mcdonalds.kmmagentcore

import com.mcdonalds.kmmagentcore.presentation.AgentOrchestrator
import com.mcdonalds.kmmagentcore.presentation.ScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * iOS-only bridge that adapts the shared [AgentOrchestrator] StateFlow into a
 * simple callback API, since Swift cannot collect Kotlin flows directly.
 *
 * Usage from Swift:
 * ```swift
 * let observer = ScreenStateObserver(orchestrator: orchestrator)
 * observer.observe { state in ... }   // called on the main thread
 * observer.dispose()                  // when the view disappears
 * ```
 */
class ScreenStateObserver(
    private val orchestrator: AgentOrchestrator
) {
    private val scope = CoroutineScope(Dispatchers.Main)
    private var job: Job? = null

    fun observe(onChange: (ScreenState) -> Unit) {
        job?.cancel()
        job = scope.launch {
            orchestrator.state.collect { onChange(it) }
        }
    }

    fun dispose() {
        job?.cancel()
        job = null
    }
}

