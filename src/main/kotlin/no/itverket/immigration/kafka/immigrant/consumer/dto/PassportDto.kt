package no.itverket.immigration.kafka.immigrant.consumer.dto

import java.util.*

data class PassportDto(
    val nationality: String,
    val socialSecurityNumber: UUID,
    val image: UUID
)