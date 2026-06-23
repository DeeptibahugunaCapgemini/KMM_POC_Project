package com.mcdonalds.kmmagentcore.data.remote

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

/** Android backs the Ktor client with the OkHttp engine. */
actual fun httpClientEngine(): HttpClientEngine = OkHttp.create()

