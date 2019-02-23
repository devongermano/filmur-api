package io.kotin.api.models

import com.fasterxml.jackson.annotation.JsonCreator
import java.util.*


data class NewUser @JsonCreator constructor(
        val email: String);

data class User (override val id: Number,
                 override val dateCreated: Date,
                 override val dateModified: Date,
                 override val deleted: Boolean = false,
                 override val dateDeleted: Date?,
                 val email: String): IBase, IDeletable;