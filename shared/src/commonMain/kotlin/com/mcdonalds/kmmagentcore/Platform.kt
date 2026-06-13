package com.mcdonalds.kmmagentcore

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform