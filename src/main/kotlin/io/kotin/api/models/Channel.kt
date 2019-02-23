package io.kotin.api.models

import com.fasterxml.jackson.annotation.JsonCreator
import java.util.*

data class NewChannel @JsonCreator constructor(
        val name: String
)

data class Channel(
        override val id: Number,
        val name: String,
        override val dateCreated: Date,
        override val dateModified: Date,
        override val deleted: Boolean = false,
        override val dateDeleted: Date?) : IBase, IDeletable