package io.kotin.api.models

data class BearerAccessToken(
        val access_token: String,
        val expires_in: Int,
        val scope: String,
        val token_type: String
)