package io.kotin.api.models

import com.fasterxml.jackson.annotation.JsonCreator
import io.kotin.api.configuration.AppConfiguration

data class Auth0TokenRequest @JsonCreator constructor(
        val grant_type: String,
        val client_id: String,
        val client_secret: String,
        val audience: String
) {

    companion object {
        fun fromConfiguration(appConfiguration: AppConfiguration): Auth0TokenRequest {
            return Auth0TokenRequest(appConfiguration.auth0.grant_type,
                                        appConfiguration.auth0.client_id,
                                        appConfiguration.auth0.client_secret,
                                        appConfiguration.auth0.audience)
        }
    }
}