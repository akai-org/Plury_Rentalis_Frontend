package org.akai.pluryrenatlisapp.apiclient

import kotlinx.serialization.Serializable

@Serializable
data class Authorization (
    var token: String,
) {
    constructor() : this("")


    override fun toString(): String {
        return token
    }

    override fun hashCode(): Int {
        return token.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Authorization

        return token == other.token
    }
}
