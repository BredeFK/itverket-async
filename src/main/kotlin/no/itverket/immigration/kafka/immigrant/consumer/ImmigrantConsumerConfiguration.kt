package no.itverket.immigration.kafka.immigrant.consumer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@Configuration
@EnableKafka
internal class ImmigrantConsumerConfiguration {

    @Bean("immigrantListenerContainerFactory")
    fun immigrantListenerContainerFactory(
        properties: ImmigrantConsumerProperties
    ) = ConcurrentKafkaListenerContainerFactory<String, String>().apply {
        setConcurrency(1)
        consumerFactory = DefaultKafkaConsumerFactory(properties.config)
        containerProperties.pollTimeout = Long.MAX_VALUE
    }

}