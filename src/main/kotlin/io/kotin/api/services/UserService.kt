package io.kotin.api.services

import io.kotin.api.configuration.AppConfiguration
import io.kotin.api.models.Auth0User
import io.kotin.api.models.NewAuth0User
import io.kotin.api.models.NewUser
import io.kotin.api.models.User
import io.kotin.api.respositories.UserRepository
import io.kotin.api.utility.copyNonNullProperties
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var auth0Service: Auth0Service;
    @Autowired
    lateinit var appConfiguration: AppConfiguration;

    fun get(id: String): User {
        return userRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User ID: ${id} not found.");
        }
    }

    fun create(newUser: NewUser): User {

        val uri = "${appConfiguration.auth0.endpoint.base}${appConfiguration.auth0.endpoint.users}"

        val restTemplate = RestTemplate()
        val headers = HttpHeaders()

        headers.set("Authorization", "Bearer ${auth0Service.getManagementToken().access_token}")

        val newAuth0User = NewAuth0User(newUser)
        val entity = HttpEntity(newAuth0User, headers)

        val response: ResponseEntity<Auth0User>
        try {
            response = restTemplate.exchange(uri, HttpMethod.POST, entity, Auth0User::class.java)
        } catch (ex: HttpStatusCodeException) {
            throw ResponseStatusException(ex.statusCode, ex.message, ex);
        }

        val user = User(response.body!!)

        return userRepository.insert(user)
    }

    fun update(user: User): User {

        val existingUser = userRepository.findByIdOrNull(user.id)
        if (existingUser == null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // Only copies properties from user to existingUser that are not null, such that we don't write over good data
        copyNonNullProperties(user, existingUser)

        val updatedUser = user.copy(dateModified = Date())

        return this.userRepository.save(existingUser)
    }
}