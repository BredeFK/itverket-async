package no.itverket.immigration.immigrationprocess.passport

import no.itverket.immigration.immigrationprocess.ImmigrationProcess
import no.itverket.immigration.immigrationprocess.ImmigrationProcessResult
import no.itverket.immigration.kafka.immigrant.consumer.dto.PassportDto
import java.util.UUID

class PassportImmigrationProcess private constructor(
    processId: UUID,
    private val image: UUID,
    private val face: UUID
): ImmigrationProcess(processId) {

    constructor(processId: UUID, face: UUID, passport: PassportDto): this(
        processId, passport.image, face
    )

    override fun process() =
        if (image == face) result(ImmigrationProcessResult.RESIDENT)
        else result(ImmigrationProcessResult.INVALID_PASSPORT_IMAGE)
}