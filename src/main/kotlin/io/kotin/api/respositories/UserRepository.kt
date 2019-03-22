package io.kotin.api.respositories

import io.kotin.api.models.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {
    fun findByIdentitiesContaining(user_id: String): User

    fun findByIdentitiesWithin(user_id: String) : User
}