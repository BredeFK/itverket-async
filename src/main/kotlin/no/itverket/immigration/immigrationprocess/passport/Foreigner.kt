package no.itverket.immigration.immigrationprocess.passport

import no.itverket.immigration.immigrationprocess.ImmigrationProcess
import no.itverket.immigration.immigrationprocess.ImmigrationProcessResult
import no.itverket.immigration.kafka.immigrationresult.producer.dto.ImmigrationResultDto
import java.util.*

class Foreigner(
    private val nationality: String,
    processId: UUID
): ImmigrationProcess(processId) {

    companion object {
        private val PERMITTED_NATIONS = listOf("omegapointistan", "fire-nation")
    }

    override fun process() =
        if (permittedNation()) result(ImmigrationProcessResult.FOREIGNER)
        else result(ImmigrationProcessResult.INVALID_FOREIGN_NATION)

    private fun permittedNation() = nationality.lowercase() in PERMITTED_NATIONS
}