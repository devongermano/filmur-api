package io.kotin.api.models

import com.fasterxml.jackson.annotation.JsonIgnore
import io.kotin.api.interfaces.IBase
import io.kotin.api.interfaces.IDeletable
import org.springframework.data.annotation.Id
import java.util.*

interface UserBase {
    var id: String?
    val username: String
    val channels: List<Channel>
}

data class User (@Id override var id: String? = null,
                 @JsonIgnore override val dateCreated: Date = Date(),
                 @JsonIgnore override val dateModified: Date = Date(),
                 @JsonIgnore override val deleted: Boolean = false,
                 @JsonIgnore override val dateDeleted: Date? = null,
                 override val username: String = "",
                 override val channels: List<Channel> = emptyList(),
                 val email: String = "",
                 val identities: List<Identity> = emptyList()): UserBase, IBase, IDeletable {

    // We don't store anything important in Auth0, should make migration to a new provider in
    // the future just a bit easier.
    constructor(auth0User: Auth0User): this(email = auth0User.email,
            identities = auth0User.identities) {}
}