package io.kotin.api.models

data class BearerAccessToken (
        val access_token: String,
        val scope: String,
        val expires_in: Int,
        val token_type: String
)