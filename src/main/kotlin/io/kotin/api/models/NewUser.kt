package io.kotin.api.models

import com.fasterxml.jackson.annotation.JsonCreator

data class NewUser @JsonCreator constructor(val email: String, val password: String)
