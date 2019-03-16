package io.kotin.api.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConfigurationProperties(prefix = "auth0.url")
@Configuration("auth0Endpoints")
class Auth0Endpoints {
    lateinit var base: String
    lateinit var token: String

    lateinit var users: String
}