package org.akai.pluryrenatlisapp.apiclient

import io.ktor.client.statement.HttpResponse
import org.akai.pluryrenatlisapp.data.Rent
import org.akai.pluryrenatlisapp.data.User

interface RentalisApiServiceInterface {
    suspend fun authorize(authParams: Map<String, Any>): Authorization

    suspend fun register(registerParams: Map<String, Any>): Authorization

    suspend fun returnRent(auth: Authorization, rent: Rent): HttpResponse

    suspend fun getUser(auth: Authorization, searchTokens: Map<UserSearchToken, Any>): User
    suspend fun rent(authorization: Authorization, rent: Rent): HttpResponse
    suspend fun getRentalsWithCurrentRenters(
        auth: Authorization,
        searchTokens: List<RentalSearchToken>,
        userUUID: String?
    ): List<Rent>
}