package no.itverket.immigration.kafka.immigrant.consumer.dto

import no.itverket.immigration.immigrationprocess.ImmigrationProcessResult
import no.itverket.immigration.immigrationprocess.ValidEmployer
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
            if (ValidEmployer.values()
                    .any { validEmployer -> validEmployer.employerName == visa.employer }
            ) {
                ImmigrationProcessResult.VISA_IMMIGRANT
            } else {
                ImmigrationProcessResult.INVALID_EMPLOYER
            }
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