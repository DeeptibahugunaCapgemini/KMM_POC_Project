package com.example.kotlinproject.domain.model

/**
 * Strongly-typed set of components the native renderers know how to draw.
 * UNKNOWN acts as a safe fallback for forward-compatible schemas.
 */
enum class ComponentType {
    COLUMN, ROW, TEXT, BUTTON, IMAGE, SPACER, UNKNOWN
}

