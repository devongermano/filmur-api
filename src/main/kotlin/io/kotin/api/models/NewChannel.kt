package io.kotin.api.models

import com.fasterxml.jackson.annotation.JsonCreator

data class NewChannel @JsonCreator constructor(
        val name: String
)