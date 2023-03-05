package no.itverket.kafka.consumer

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
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