package no.itverket.immigration.kafka.immigrant.consumer.dto

import java.util.*

data class VisaDto(
    val employer: String,
    val employeeId: UUID,
    val image: UUID
)