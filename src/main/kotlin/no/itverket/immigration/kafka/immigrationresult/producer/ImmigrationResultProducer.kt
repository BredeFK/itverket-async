package no.itverket.immigration.kafka.immigrationresult.producer

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import no.itverket.immigration.immigrationprocess.ImmigrationProcessResult
import no.itverket.immigration.kafka.immigrationresult.producer.dto.ImmigrationResultDto
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*
import kotlin.time.Duration

@Service
class ImmigrationResultProducer(
    private val immigrationResultProducerProperties: ImmigrationResultProducerProperties,
    @Value("\${kafka.immigrant.consumer.group-id}")
    private val groupId: String
) {

    companion object {
        private val objectMapper = jacksonObjectMapper()
    }

    private val producer = KafkaProducer<String, String>(
        immigrationResultProducerProperties.producerProperties
    )

    fun publishImmigrant(result: ImmigrationResultDto, duration: Duration) {
        producer.send(
            ProducerRecord(
                immigrationResultProducerProperties.topic,
                groupId,
                objectMapper.writeValueAsString(
                    if (duration.inWholeMilliseconds > 10000) result.copy(
                        permitted = false,
                        reason = ImmigrationProcessResult.GOT_TIRED_OF_WAITING
                    )
                    else result
                )
            )
        )
    }
}