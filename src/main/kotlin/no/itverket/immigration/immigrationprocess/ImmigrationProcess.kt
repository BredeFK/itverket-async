package no.itverket.immigration.immigrationprocess

import no.itverket.immigration.immigrationprocess.passport.PassportImmigrationProcess
import no.itverket.immigration.immigrationprocess.visa.VisaImmigrationProcess
import no.itverket.immigration.kafka.immigrant.consumer.dto.ImmigrantDto
import no.itverket.immigration.kafka.immigrationresult.producer.dto.ImmigrationResultDto
import java.util.*

abstract class ImmigrationProcess(
    protected val processId: UUID
) {
    companion object {
        private fun startVisaImmigrationProcess(processId: UUID, immigrant: ImmigrantDto) = immigrant.visa?.let {
            VisaImmigrationProcess(processId, immigrant.face, it)
        }

        private fun startPassportImmigrationProcess(processId: UUID, immigrant: ImmigrantDto) = immigrant.passport?.let {
            PassportImmigrationProcess(processId, immigrant.face, it)
        }

        fun startImmigrationProcess(processId: UUID, immigrant: ImmigrantDto) =
            startVisaImmigrationProcess(processId, immigrant) ?:
            startPassportImmigrationProcess(processId, immigrant) ?:
            throw Exception()
    }

    abstract fun process(): ImmigrationResultDto

    protected fun result(result: ImmigrationProcessResult) = ImmigrationResultDto(
        processId, result.permitted, result
    )
}