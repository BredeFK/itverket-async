package no.itverket.immigration.immigrationprocess.registry

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class RegistryWebClientConfiguration(
    @Value("\${host-address}")
    hostAddress: String,

    @Value("\${registry-port}")
    port: String,
) {
    private val baseUrl = "http://$hostAddress:$port"

    @Bean
    fun registryWebClient(): WebClient =
        WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeaders {
                it.contentType = MediaType.APPLICATION_JSON
                it.accept = listOf(MediaType.APPLICATION_JSON)
            }.build()
}