package no.itverket.immigration.kafka.immigrationresult.producer

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Service

@Service
class ImmigrationResultProducer(
    private val immigrationResultProducerProperties: ImmigrationResultProducerProperties
) {

    private val producer = KafkaProducer<String, String>(
        immigrationResultProducerProperties.producerProperties
    )

    fun publishImmigrant(message: String) {
        producer.send(
            ProducerRecord(
                immigrationResultProducerProperties.topic,
                "hei",
                message
            )
        )
    }
}