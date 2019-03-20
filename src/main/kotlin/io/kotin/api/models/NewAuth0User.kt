package io.kotin.api.models

data class NewAuth0User (
        val email: String,
        val password: String,
        val connection: String = "Username-Password-Authentication") {

    constructor(newUser: NewUser): this(email = newUser.email,
            password = newUser.password)
}