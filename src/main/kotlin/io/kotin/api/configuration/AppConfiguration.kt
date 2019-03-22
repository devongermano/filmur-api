package io.kotin.api.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConfigurationProperties(prefix = "app")
@Configuration("appConfiguration")
@EnableConfigurationProperties(AppConfiguration::class)
class AppConfiguration {

    var auth0 = Auth0Properties()

    companion object {

        class Auth0Properties {
            var endpoint: Auth0EndpointProperties = Auth0EndpointProperties()
            var web: Auth0WebProperties = Auth0WebProperties()
            var management: Auth0ManagementProperties = Auth0ManagementProperties()
        }

        class Auth0EndpointProperties {
            lateinit var base: String
            lateinit var token: String
            lateinit var users: String
        }

        class Auth0ManagementProperties {
            lateinit var grant_type: String;
            lateinit var client_id: String;
            lateinit var client_secret: String;
            lateinit var audience: String
        }

        class Auth0WebProperties {
            lateinit var client_id: String;
            lateinit var issuer: String
        }
    }
}