package com.mcdonalds.kmmagentcore.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Supplies the platform-specific Ktor engine.
 * Android uses OkHttp, iOS uses Darwin — both implemented in their
 * respective source sets via `actual`.
 */
expect fun httpClientEngine(): HttpClientEngine

/**
 * Shared, lenient JSON configuration reused for both Ktor content
 * negotiation and manual parsing in the data layer.
 */
val defaultJson: Json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

/**
 * Builds a configured [HttpClient]. Create a single instance and reuse it
 * for the app's lifetime; spinning up a client per request is expensive.
 *
 * - `expectSuccess = true` turns non-2xx responses into exceptions so the
 *   orchestration layer can surface them as error states.
 * - [ContentNegotiation] is installed for future typed requests.
 * - [HttpTimeout] guards against hung connections.
 */
fun createHttpClient(
    engine: HttpClientEngine = httpClientEngine(),
    json: Json = defaultJson
): HttpClient = HttpClient(engine) {
    expectSuccess = true

    install(ContentNegotiation) {
        json(json)
    }

    install(HttpTimeout) {
        requestTimeoutMillis = 30_000
        connectTimeoutMillis = 15_000
        socketTimeoutMillis = 15_000
    }

    install(Logging) {
        level = LogLevel.INFO
    }
}

