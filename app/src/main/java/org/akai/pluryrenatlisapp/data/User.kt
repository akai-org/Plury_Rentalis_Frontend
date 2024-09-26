package org.akai.pluryrenatlisapp.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var name: String = "",
    var email: String = "",
    var uuid: String? = null
)
