package io.kotin.api.models

import com.fasterxml.jackson.annotation.JsonCreator

data class NewVideo @JsonCreator constructor(
        val title: String,
        val description: String
)