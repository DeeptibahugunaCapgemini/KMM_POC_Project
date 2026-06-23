package com.mcdonalds.kmmagentcore.data.datasource

import com.mcdonalds.kmmagentcore.data.remote.createHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType

/**
 * Real [LayoutRemoteDataSource] that fetches the raw layout JSON from the
 * agent backend over HTTP using a Ktor multiplatform client.
 *
 * It intentionally returns the response as a [String] so the existing
 * repository keeps full control over parsing/mapping — the contract with
 * the rest of the app is unchanged; only the source of the JSON differs.
 *
 * @param client reusable Ktor client (inject a shared one in production).
 * @param baseUrl root of the layout API, e.g. `https://host/v1`.
 */
class KtorLayoutDataSource(
    private val client: HttpClient = createHttpClient(),
    private val baseUrl: String = DEFAULT_BASE_URL
) : LayoutRemoteDataSource {

    override suspend fun fetchLayoutJson(screenId: String): String =
        client.get("$baseUrl/$LAYOUTS_PATH/$screenId") {
            accept(ContentType.Application.Json)
        }.bodyAsText()

    companion object {
        /** Replace with your real backend host. */
        const val DEFAULT_BASE_URL: String = "https://api.kmm-agent.example.com"
        private const val LAYOUTS_PATH: String = "v1/layouts"
    }
}

