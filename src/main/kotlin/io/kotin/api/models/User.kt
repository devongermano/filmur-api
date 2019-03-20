package io.kotin.api.models

import io.kotin.api.interfaces.IBase
import io.kotin.api.interfaces.IDeletable
import org.springframework.data.annotation.Id
import java.util.*

data class User (@Id override var id: String? = null,
                 override val dateCreated: Date = Date(),
                 override val dateModified: Date = Date(),
                 override val deleted: Boolean = false,
                 override val dateDeleted: Date? = null,
                 val email: String = "",
                 val username: String = "",
                 val channels: List<Channel> = emptyList(),
                 val identities: List<Identity> = emptyList()): IBase, IDeletable {

    // We don't store anything important in Auth0, should make migration to a new provider in
    // the future just a bit easier.
    constructor(auth0User: Auth0User): this(email = auth0User.email,
            identities = auth0User.identities) {}
}