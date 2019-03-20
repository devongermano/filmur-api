package io.kotin.api.models

import io.kotin.api.interfaces.IBase
import io.kotin.api.interfaces.IDeletable
import org.springframework.data.annotation.Id
import java.util.*

data class Subscription(@Id override var id: String? = null,
                        val followerId: Number,
                        val channelId: Number,
                        override val dateCreated: Date,
                        override val dateModified: Date,
                        override val deleted: Boolean = false,
                        override val dateDeleted: Date?) : IBase, IDeletable