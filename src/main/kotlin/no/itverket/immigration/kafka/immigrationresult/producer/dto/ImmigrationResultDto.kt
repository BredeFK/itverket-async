package no.itverket.immigration.kafka.immigrationresult.producer.dto

import java.util.*

data class ImmigrationResultDto(
    val processId: UUID,
    val permitted: Boolean,
    val reason: String
)