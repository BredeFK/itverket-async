package no.itverket.immigration.immigrationprocess.registry

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.util.UriComponentsBuilder
import java.util.UUID

@Service
class Registry(
    private val registryWebClient: WebClient
) {
    private fun employeeRegistrationUri(employeeId: UUID, employer: String) = UriComponentsBuilder
        .fromPath("registry/employee")
        .queryParam("employeeId", employeeId)
        .queryParam("employer", employer)
        .build(true)
        .toUriString()

    suspend fun employeeRegistered(employeeId: UUID, employer: String) =
        registryWebClient.get()
            .uri(employeeRegistrationUri(employeeId, employer))
            .retrieve()
            .awaitBody<Boolean>()

    private fun foreignerRegistrationUri(ssn: UUID, nationality: String) = UriComponentsBuilder
        .fromPath("registry/foreigner")
        .queryParam("ssn", ssn)
        .queryParam("nationality", nationality)
        .build(true)
        .toUriString()

    suspend fun foreignerRegistered(ssn: UUID, nationality: String) =
        registryWebClient.get()
            .uri(foreignerRegistrationUri(ssn, nationality))
            .retrieve()
            .awaitBody<Boolean>()

    private fun residentRegistrationUri(ssn: UUID) = UriComponentsBuilder
        .fromPath("registry/resident")
        .queryParam("ssn", ssn)
        .build(true)
        .toUriString()

    suspend fun residentRegistered(ssn: UUID) =
        registryWebClient.get()
            .uri(residentRegistrationUri(ssn))
            .retrieve()
            .awaitBody<Boolean>()

}