package no.itverket.immigration.kafka.immigrant.consumer

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import no.itverket.immigration.immigrationprocess.ImmigrationProcess
import no.itverket.immigration.kafka.immigrant.consumer.dto.ImmigrantDto
import no.itverket.immigration.kafka.immigrationresult.producer.ImmigrationResultProducer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.util.*


@Service
internal class ImmigrantConsumer(
    private val producer: ImmigrationResultProducer
) {

    companion object {
        private val objectMapper = jacksonObjectMapper()
    }
    @KafkaListener(
        topics = ["\${kafka.immigrant.consumer.topic}"],
        containerFactory = "immigrantListenerContainerFactory"
    )
    fun receive(message: ConsumerRecord<String, String>) {
        val processId = UUID.fromString(message.key())
        val immigrant: ImmigrantDto = jacksonObjectMapper().readValue(message.value())
        val result = ImmigrationProcess.startImmigrationProcess(processId, immigrant).process()
        producer.publishImmigrant(result)
    }
}