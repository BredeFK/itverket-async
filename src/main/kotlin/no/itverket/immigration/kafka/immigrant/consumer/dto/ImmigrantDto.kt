package no.itverket.immigration.kafka.immigrant.consumer.dto

import no.itverket.immigration.immigrationprocess.ImmigrationProcessResult
import java.util.*

data class ImmigrantDto(
    val visa: VisaDto?,
    val passport: PassportDto?,
    val face: UUID
) {
    init {
        require((visa == null) xor (passport == null))
    }

    fun isValidVisa(): Boolean {
        return visa != null
    }

    fun getVisaProcessResult(): ImmigrationProcessResult {
        return if (visa?.image == face) {
            visa.processEmployer()
        } else {
            ImmigrationProcessResult.INVALID_VISA_IMAGE
        }
    }

    fun getPassportProcessResult(): ImmigrationProcessResult {
        return if (passport?.image == face) {
            passport.processNationality()
        } else {
            ImmigrationProcessResult.INVALID_PASSPORT_IMAGE
        }
    }
}