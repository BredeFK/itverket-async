package no.itverket.immigration.immigrationprocess.visa

import no.itverket.immigration.immigrationprocess.ImmigrationProcess
import no.itverket.immigration.immigrationprocess.ImmigrationProcessResult
import no.itverket.immigration.kafka.immigrant.consumer.dto.VisaDto
import java.util.*

class VisaImmigrationProcess private constructor(
    processId: UUID,
    private val image: UUID,
    private val face: UUID
): ImmigrationProcess(processId) {

    constructor(processId: UUID, face: UUID, visa: VisaDto): this(
        processId = processId,
        image = visa.image,
        face = face
    )

    override fun process() =
        if (image == face) result(ImmigrationProcessResult.VISA_IMMIGRANT)
        else result(ImmigrationProcessResult.INVALID_VISA_IMAGE)
}