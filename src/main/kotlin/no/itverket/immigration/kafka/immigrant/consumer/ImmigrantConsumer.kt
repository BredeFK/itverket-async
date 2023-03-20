package no.itverket.immigration.kafka.immigrant.consumer

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import no.itverket.immigration.immigrationprocess.ImmigrationProcess
import no.itverket.immigration.immigrationprocess.ImmigrationProcessResult
import no.itverket.immigration.immigrationprocess.registry.Registry
import no.itverket.immigration.kafka.immigrant.consumer.dto.ImmigrantDto
import no.itverket.immigration.kafka.immigrationresult.producer.ImmigrationResultProducer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue


@Service
internal class ImmigrantConsumer(
    private val producer: ImmigrationResultProducer,
    private val registry: Registry
) {

    companion object {
        private val objectMapper = jacksonObjectMapper()
    }
    @OptIn(ExperimentalTime::class)
    @KafkaListener(
        topics = ["\${kafka.immigrant.consumer.topic}"],
        containerFactory = "immigrantListenerContainerFactory"
    )
    fun receive(message: ConsumerRecord<String, String>) {
        val processId = UUID.fromString(message.key())
        val immigrant: ImmigrantDto = jacksonObjectMapper().readValue(message.value())
        val (result, duration) = measureTimedValue { ImmigrationProcess(processId, immigrant, registry).process() }
        producer.publishImmigrant(result, duration)
    }
}