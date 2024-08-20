package org.akai.pluryrenatlisapp.data

data class Rentable(
    val name: String,
    val type:  RentableType,
    var renter: String? = null,
    var uuid: String = "",
)