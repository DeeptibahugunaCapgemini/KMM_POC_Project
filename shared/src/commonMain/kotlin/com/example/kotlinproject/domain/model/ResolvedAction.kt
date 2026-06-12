package com.example.kotlinproject.domain.model

/**
 * Describes an action a component can trigger (e.g. navigation),
 * along with an arbitrary payload the host app can interpret.
 */
data class ResolvedAction(
    val type: String,
    val payload: Map<String, String>
)

