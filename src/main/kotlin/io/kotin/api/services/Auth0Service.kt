package io.kotin.api.services

import io.kotin.api.configuration.Auth0Endpoints
import io.kotin.api.models.BearerAccessToken
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.beans.factory.annotation.Autowired





@Service
class Auth0Service {

    @Autowired
    lateinit var auth0Endpoints: Auth0Endpoints

    fun getManagementToken(): BearerAccessToken {
        if (managementToken != null) {
            return managementToken!!
        } else {
            val uri = "${auth0Endpoints.base}${auth0Endpoints.token}"
            val restTemplate = RestTemplate()
            val result = restTemplate.getForObject(uri, BearerAccessToken::class.java)
            managementToken = result
            println(result)
            return managementToken!!
        }
    }

    companion object {
        private var managementToken: BearerAccessToken? = null
    }
}