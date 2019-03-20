package io.kotin.api.respositories

import io.kotin.api.models.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {
}