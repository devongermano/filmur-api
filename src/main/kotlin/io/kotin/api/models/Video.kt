package io.kotin.api.models

import com.fasterxml.jackson.annotation.JsonCreator
import io.kotin.api.interfaces.IBase
import io.kotin.api.interfaces.IDeletable
import java.util.*

data class NewVideo @JsonCreator constructor(
        val title: String,
        val description: String
)

data class Video(
        override val id: Number,
        override val dateCreated: Date,
        override val dateModified: Date,
        override val deleted: Boolean = false,
        override val dateDeleted: Date?,
        val title: String,
        val description: String,
        val length: Number,
        val likes: Number = 0,
        val dislikes: Number = 0,
        val url: String
): IBase, IDeletable