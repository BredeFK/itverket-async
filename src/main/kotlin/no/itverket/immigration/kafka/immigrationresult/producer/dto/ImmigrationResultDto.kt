package no.itverket.immigration.kafka.immigrationresult.producer.dto

import no.itverket.immigration.immigrationprocess.ImmigrationProcessResult
import java.util.*

data class ImmigrationResultDto(
    val processId: UUID,
    val permitted: Boolean,
    val reason: ImmigrationProcessResult
)