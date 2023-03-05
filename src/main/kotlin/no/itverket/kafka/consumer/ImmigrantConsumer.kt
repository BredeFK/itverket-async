package no.itverket.kafka.consumer

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service


@Service
internal class ImmigrantConsumer {
    @KafkaListener(
        topics = ["itverket-immigration"],
        containerFactory = "immigrantListenerContainerFactory"
    )
    fun receive(message: ConsumerRecord<String, String>) {
        println("CONSOOOOMING")
        println(message.value())
    }
}