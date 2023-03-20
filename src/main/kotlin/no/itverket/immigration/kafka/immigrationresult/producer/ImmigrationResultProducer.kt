package no.itverket.immigration.kafka.immigrationresult.producer

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import no.itverket.immigration.immigrationprocess.ImmigrationProcessResult
import no.itverket.immigration.kafka.immigrationresult.producer.dto.ImmigrationResultDto
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Service
import java.util.*

@Service
class ImmigrationResultProducer(
    private val immigrationResultProducerProperties: ImmigrationResultProducerProperties
) {

    companion object {
        private val objectMapper = jacksonObjectMapper()
    }

    private val producer = KafkaProducer<String, String>(
        immigrationResultProducerProperties.producerProperties
    )

    fun publishImmigrant(processId: UUID, result: ImmigrationResultDto) {
        producer.send(
            ProducerRecord(
                immigrationResultProducerProperties.topic,
                processId.toString(),
                objectMapper.writeValueAsString(result)
            )
        )
    }
}