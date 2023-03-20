package no.itverket.immigration.kafka.immigrationresult.producer

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "kafka.immigration-result.producer")
class ImmigrationResultProducerProperties(
    val topic: String,
    ipAddress: String,
    port: String,
    clientId: String,
    acksConfig: String
) {
   val producerProperties = mapOf(
        ProducerConfig.CLIENT_ID_CONFIG to clientId,
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "$ipAddress:$port",
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
        ProducerConfig.ACKS_CONFIG to acksConfig
    )
}