package no.itverket.immigration.kafka.immigrant.consumer.dto

import no.itverket.immigration.immigrationprocess.ImmigrationProcessResult
import no.itverket.immigration.immigrationprocess.ValidEmployer
import java.util.*

data class VisaDto(
    val employer: String,
    val employeeId: UUID,
    val image: UUID
) {
    fun processEmployer(): ImmigrationProcessResult {
        return if (ValidEmployer.values()
                .any { validEmployer -> validEmployer.employerName == employer }
        ) {
            ImmigrationProcessResult.VISA_IMMIGRANT
        } else {
            ImmigrationProcessResult.INVALID_EMPLOYER
        }
    }
}