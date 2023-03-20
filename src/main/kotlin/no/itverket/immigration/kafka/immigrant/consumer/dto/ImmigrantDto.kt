package no.itverket.immigration.kafka.immigrant.consumer.dto

import java.util.*

data class ImmigrantDto(
    val visa: VisaDto?,
    val passport: PassportDto?,
    val face: UUID
) {
    init {
        require((visa==null) xor (passport==null))
    }
}