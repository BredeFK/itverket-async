package no.itverket

import no.itverket.kafka.consumer.ImmigrantConsumerConfiguration
import no.itverket.kafka.consumer.ImmigrantConsumerProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory

@SpringBootApplication
@EnableConfigurationProperties(ImmigrantConsumerProperties::class)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}