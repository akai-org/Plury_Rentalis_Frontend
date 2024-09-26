package org.akai.pluryrenatlisapp.data

import kotlinx.serialization.Serializable

@Serializable
data class Rentable(
    val name: String,
    val type:  RentableType,
    var uuid: String = "",
)