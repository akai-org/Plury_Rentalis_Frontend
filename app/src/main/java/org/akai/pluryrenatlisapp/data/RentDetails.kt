package org.akai.pluryrenatlisapp.data

import java.time.Instant

data class RentDetails(
    val uuid: String,
    val renter: User,
    val rentable: Rentable,
    val rentDate: Instant,
    val returnDate: Instant,
)
