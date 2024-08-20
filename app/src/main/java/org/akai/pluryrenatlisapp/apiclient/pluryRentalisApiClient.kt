package org.akai.pluryrenatlisapp.apiclient

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.cache.storage.FileStorage
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.io.File

private const val timeout = 10_000
val pluryRentalisApiClient = HttpClient(Android) {
    engine {
        connectTimeout = timeout
        socketTimeout = timeout
    }

    install(HttpTimeout) {
        requestTimeoutMillis = timeout.toLong()
        connectTimeoutMillis = timeout.toLong()
        socketTimeoutMillis = timeout.toLong()
    }

    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }

    install(DefaultRequest) {
        headers.append("Content-Type", "application/json")
        headers.append("Accept", "application/json")
        headers.append("X-API-KEY", "P1URY_R3NT4L_1S")
        url {
            protocol = URLProtocol.HTTPS
            host = "api.pluryerentalis.com"
        }
    }

    install(HttpCache) {
        publicStorage(FileStorage(File("apiCache")))
    }



}