package com.example.kotlinproject.data.remote

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

/** iOS backs the Ktor client with the Darwin (NSURLSession) engine. */
actual fun httpClientEngine(): HttpClientEngine = Darwin.create()

