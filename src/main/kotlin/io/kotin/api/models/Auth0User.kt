package io.kotin.api.models

import com.fasterxml.jackson.annotation.JsonCreator

data class Auth0User @JsonCreator constructor(
        val user_id: String,
        val email: String,
        val email_verified: Boolean,
        val phone_number: String = "",
        val phone_verified: Boolean,
        val blocked: Boolean,
        val identities: List<Identity>)