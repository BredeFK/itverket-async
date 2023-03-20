package no.itverket.immigration

import no.itverket.immigration.kafka.immigrant.consumer.ImmigrantConsumerProperties
import no.itverket.immigration.kafka.immigrationresult.producer.ImmigrationResultProducerProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(value = [ImmigrantConsumerProperties::class, ImmigrationResultProducerProperties::class])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}