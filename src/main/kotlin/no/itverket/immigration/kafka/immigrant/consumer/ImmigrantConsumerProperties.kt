package no.itverket.immigration.kafka.immigrant.consumer

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "kafka.immigrant.consumer")
internal class ImmigrantConsumerProperties(
    ipAddress: String,
    port: String,
    groupId: String
) {
    val config = mapOf(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "$ipAddress:$port",
        ConsumerConfig.GROUP_ID_CONFIG to groupId,
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
        ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest"
    )
}