package io.kotin.api.models

import io.kotin.api.interfaces.IBase
import io.kotin.api.interfaces.IDeletable
import org.springframework.data.annotation.Id
import java.util.*

data class Video(
        @Id override var id: String? = null,
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