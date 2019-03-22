package io.kotin.api.services

import io.kotin.api.configuration.AppConfiguration
import io.kotin.api.models.*
import io.kotin.api.respositories.UserRepository
import io.kotin.api.utility.copyNonNullProperties
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.*
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException
import java.util.*
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.findOne
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.isEqualTo


@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var auth0Service: Auth0Service;
    @Autowired
    lateinit var appConfiguration: AppConfiguration;

    @Autowired
    lateinit var mongoTemplate: MongoTemplate


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

    fun getCurrent(authentication: Authentication): User {
        val subject = authentication.principal.toString()
        val subjectSplit = subject.split("|")

        if(subjectSplit.count() != 2) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "The authentication token principal could not be parsed in its current state.");
        }

        val query = Query()
        Criteria.where("stringArray")
        query.addCriteria(Criteria.where("identities.user_id").isEqualTo(subjectSplit[1]))
        val user = mongoTemplate.findOne(query, User::class.java)

        if(user == null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find a valid filmur user with the provided authentication principal.");
        }
        return user
    }

    fun updateCurrent(authentication: Authentication, user: User): User {

        val existingUser = userRepository.findByIdOrNull(user.id)
        if (existingUser == null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        val updatedUser = user.copy(
                dateCreated = existingUser.dateCreated,
                dateModified = Date(),
                deleted = existingUser.deleted,
                dateDeleted = existingUser.dateDeleted,
                channels = existingUser.channels,
                identities = existingUser.identities)

        return this.userRepository.save(updatedUser)
    }
}