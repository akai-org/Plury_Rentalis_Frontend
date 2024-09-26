package org.akai.pluryrenatlisapp.data

import kotlinx.serialization.Serializable

@Serializable
data class Rent(
    val uuid: String = "uuid",
    val userName: String? = null,
    val rentableName: String = "obiekt",
    val rentableType: String = "obiekt",
    val rentableUUID: String? = null
) {

}
