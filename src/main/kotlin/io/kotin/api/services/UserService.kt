package io.kotin.api.services

import io.kotin.api.configuration.Auth0Endpoints
import io.kotin.api.models.NewUser
import io.kotin.api.models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class UserService {

    @Autowired
    lateinit var auth0Service: Auth0Service;
    @Autowired
    lateinit var auth0Endpoints: Auth0Endpoints;

    fun create(newUser: NewUser): User {

        val uri = "${auth0Endpoints.base}${auth0Endpoints.users}"

        val restTemplate = RestTemplate()
        val headers = HttpHeaders()

        headers.set("Authorization", "Bearer ${auth0Service.getManagementToken().access_token}")
        val entity = HttpEntity(null, headers)

        val response: ResponseEntity<User>

        try {
            response = restTemplate.exchange(uri, HttpMethod.POST, entity, User::class.java)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Jesus take the wheeeeeel", ex);
        }


        response.body.dateCreated = Date()

//        val result = restTemplate.postForObject(uri, null, BearerAccessToken::class.java)

    }
}