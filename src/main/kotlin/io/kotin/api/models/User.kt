package io.kotin.api.models

import com.fasterxml.jackson.annotation.JsonCreator
import io.kotin.api.interfaces.IBase
import io.kotin.api.interfaces.IDeletable
import java.util.*

data class NewAuth0User (
        val email: String,
        val password: String,
        val connection: String = "Username-Password-Authentication") {

    constructor(newUser: NewUser): this(email = newUser.email,
            password = newUser.password)
}

data class Auth0User (
        val user_id: String,
        val email: String,
        val email_verified: Boolean,
        val phone_number: String,
        val phone_verified: Boolean,
        val blocked: String,
        val identities: List<Identity>)

data class NewUser @JsonCreator constructor(val email: String, val password: String) {}

data class User (override var _id: Number? = null,
                 override val dateCreated: Date = Date(),
                 override val dateModified: Date = Date(),
                 override val deleted: Boolean = false,
                 override val dateDeleted: Date? = null,
                 val email: String = "",
                 val username: String = "",
                 val identities: List<Identity> = emptyList()): IBase, IDeletable {

    // We don't store anything important in Auth0, should make migration to a new provider in
    // the future just a bit easier.
    constructor(auth0User: Auth0User): this(email = auth0User.email,
            identities = auth0User.identities) {}
}

// Really I made this for a future migration if Filmur gets too big, and we
// don't want to pay for a 3rd part authentication provider. Should let us know where
// the user's PII is located. Is based off Auth0's identity property...^^^
data class Identity(val user_id: String, val provider: String)