package io.kotin.api.models

import com.fasterxml.jackson.annotation.JsonCreator
import io.kotin.api.interfaces.IBase
import io.kotin.api.interfaces.IDeletable
import java.util.*

data class NewSubscription @JsonCreator constructor(val followerId: Number,
                                                    val channelId: Number)

data class Subscription(override val id: Number,
                        val followerId: Number,
                        val channelId: Number,
                        override val dateCreated: Date,
                        override val dateModified: Date,
                        override val deleted: Boolean = false,
                        override val dateDeleted: Date?) : IBase, IDeletable