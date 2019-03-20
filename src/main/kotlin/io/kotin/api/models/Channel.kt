package io.kotin.api.models

import io.kotin.api.interfaces.IBase
import io.kotin.api.interfaces.IDeletable
import org.springframework.data.annotation.Id
import java.util.*

data class Channel(
        @Id override var id: String? = null,
        override val dateCreated: Date,
        override val dateModified: Date,
        override val deleted: Boolean = false,
        override val dateDeleted: Date?,
        val name: String) : IBase, IDeletable