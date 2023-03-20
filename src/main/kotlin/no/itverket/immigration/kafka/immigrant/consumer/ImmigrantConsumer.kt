package no.itverket.immigration.kafka.immigrant.consumer

import no.itverket.immigration.kafka.immigrant.consumer.dto.ImmigrantDto
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.util.*


@Service
internal class ImmigrantConsumer {
    @KafkaListener(
        topics = ["\${kafka.immigrant.consumer.topic}"],
        containerFactory = "immigrantListenerContainerFactory"
    )
    fun receive(message: ConsumerRecord<UUID, ImmigrantDto>) {
        println(message.key())
        println(message.value())
    }
}