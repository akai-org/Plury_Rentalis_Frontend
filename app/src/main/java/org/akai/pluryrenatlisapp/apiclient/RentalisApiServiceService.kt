package org.akai.pluryrenatlisapp.apiclient

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.cache.storage.FileStorage
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.akai.pluryrenatlisapp.data.Rent
import org.akai.pluryrenatlisapp.data.User
import org.json.JSONObject
import java.io.File

private const val timeout = 10_000
class RentalisApiServiceService(
    private val client: HttpClient = defaultHttpClient()
): RentalisApiServiceInterface {
    override suspend fun authorize(
        authParams: Map<String, Any>
    ) = client.post {
        url {
            path("login")
        }
        setBody(JSONObject(authParams).toString())
    }.body<Authorization>()


    override suspend fun register(
        registerParams: Map<String, Any>
    ) = client.post {
        url {
            path("register")
        }
        setBody(JSONObject(registerParams).toString())
    }.body<Authorization>()

    override suspend fun returnRent(
        auth: Authorization,
        rent: Rent
    ) = client.post {
            url {
                path("rents", "return")
                headers {
                    append("Authorization", auth.token)
                }
                setBody(rent.rentableUUID)
            }
        }

    override suspend fun getUser(
        auth: Authorization,
        searchTokens: Map<UserSearchToken, Any>
    ) = client.get {
        url {
            path("user")
            searchTokens.forEach { (token, value) ->
                parameter(token.name.lowercase(), value)
            }
            headers {
                append("Authorization", auth.token)
            }
        }
    }.body<User>()

    override suspend fun getRentalsWithCurrentRenters(
        auth: Authorization,
        searchTokens: List<RentalSearchToken>,
        userUUID: String?
    ) = client.get {
        url {
            path("rents","rentable")
            searchTokens.forEach { token ->
                parameter(token.name.lowercase(), "true")
            }
            userUUID?.let {
                parameter("by", it)
            }
            headers {
                append("Authorization", auth.token)
            }
        }
    }.body<List<Rent>>()

    override suspend fun rent(
        authorization: Authorization,
        rent: Rent
    ) = client.post {
            url {
                path("rents","rent")
                headers {
                    append("Authorization", authorization.token)
                }
                setBody(rent.rentableUUID)
            }
        }

}

private fun defaultHttpClient() = HttpClient(Android) {
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
                coerceInputValues = true
                serializersModule = SerializersModule {
//                    contextual(Authorization::class, AuthorizationSerializer)
                }
            }
        )
    }

    install(DefaultRequest) {
        headers.append("Content-Type", "application/json")
        headers.append("Accept", "application/json")
        headers.append("X-API-KEY", "P1URY_R3NT4L_1S")
        url {
            protocol = URLProtocol.HTTP
            host = "192.168.0.102:8443"
        }
        contentType(ContentType.Application.Json)
    }

    install(HttpCache) {
        publicStorage(FileStorage(File("apiCache")))
    }

    install(Logging) {
        logger = Logger.ANDROID
        level = LogLevel.ALL
//            sanitizeHeader { header -> header == HttpHeaders.Authorization }
    }
}