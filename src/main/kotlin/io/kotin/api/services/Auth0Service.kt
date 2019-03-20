package io.kotin.api.services

import io.kotin.api.configuration.AppConfiguration
import io.kotin.api.models.Auth0TokenRequest
import io.kotin.api.models.BearerAccessToken
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.server.ResponseStatusException
import java.util.*


@Service
class Auth0Service {

    @Autowired
    lateinit var appConfiguration: AppConfiguration

    fun getManagementToken(): BearerAccessToken {
        if (managementToken != null) {
            return managementToken!!
        } else {
            val uri = "${appConfiguration.auth0.endpoint.base}${appConfiguration.auth0.endpoint.token}"

            val restTemplate = RestTemplate()
            val headers = HttpHeaders()
            headers.accept = Collections.singletonList(MediaType.APPLICATION_JSON)

            val entity = HttpEntity(Auth0TokenRequest.fromConfiguration(appConfiguration), headers)
            val result: ResponseEntity<BearerAccessToken>
            try {
                result = restTemplate.exchange<BearerAccessToken>(uri, HttpMethod.POST, entity, BearerAccessToken::class.java)
            } catch (ex: HttpStatusCodeException) {
                throw ResponseStatusException(ex.statusCode, "Failed to get delegation token from Auth0.", ex);
            }

            managementToken = result.body
            return managementToken!!
        }
    }

    companion object {
        private var managementToken: BearerAccessToken? = null
    }
}