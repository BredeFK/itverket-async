package no.itverket.immigration.immigrationprocess

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import no.itverket.immigration.immigrationprocess.registry.Registry
import no.itverket.immigration.kafka.immigrant.consumer.dto.ImmigrantDto
import no.itverket.immigration.kafka.immigrationresult.producer.dto.ImmigrationResultDto
import java.util.*

class ImmigrationProcess(
    private val processId: UUID,
    private val immigrant: ImmigrantDto,
    //brukes senere
    private val registry: Registry
) {

    fun process(): ImmigrationResultDto {
        runBlocking {
            launch { paperWork() }
        }

        val processResult = validateFace();


        //bruk immigrant feltet til å finne riktig ImmigrationProcessResult
        return result(processResult)
    }

    private fun result(result: ImmigrationProcessResult) = ImmigrationResultDto(
        processId, result.permitted, result
    )

    //no touchy por favor
    private suspend fun paperWork() = delay(5000)

    private fun validateFace(): ImmigrationProcessResult {
        return if (immigrant.isValidVisa())
            immigrant.getVisaProcessResult()
        else
            immigrant.getPassportProcessResult()
    }
}