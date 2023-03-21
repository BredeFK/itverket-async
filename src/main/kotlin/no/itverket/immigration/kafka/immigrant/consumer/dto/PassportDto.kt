package no.itverket.immigration.kafka.immigrant.consumer.dto

import no.itverket.immigration.immigrationprocess.Countries
import no.itverket.immigration.immigrationprocess.ImmigrationProcessResult
import java.util.*

data class PassportDto(
    val nationality: String,
    val socialSecurityNumber: UUID,
    val image: UUID
) {
    fun processNationality(): ImmigrationProcessResult {
        if (nationality == Countries.ITVERKISTAN.countryName) {
            return ImmigrationProcessResult.RESIDENT
        }

        return if (Countries.values().any { country -> country.countryName == nationality }) {
            ImmigrationProcessResult.FOREIGNER
        } else {
            ImmigrationProcessResult.INVALID_FOREIGN_NATION
        }
    }
}