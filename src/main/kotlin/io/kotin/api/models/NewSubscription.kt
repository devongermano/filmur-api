package io.kotin.api.models

import com.fasterxml.jackson.annotation.JsonCreator

data class NewSubscription @JsonCreator constructor(val followerId: Number,
                                                    val channelId: Number)