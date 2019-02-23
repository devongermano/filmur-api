package io.kotin.api.controllers

import io.kotin.api.models.NewUser
import io.kotin.api.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/user")
class UserController {

    @Autowired
    lateinit var userService: UserService;


    // Creates a user when an auth0 user is created... this is a quick fix and should be refactored later
    // such that we push the auth data from auth0 into this method, rather than request it explicitly
    @PostMapping("/callback")
    fun createUserFromAuth0(auth: Authentication, @RequestBody user: NewUser): String {
        val userId= auth.principal

        print(userId)

        return userService.hellyeah()
    }
}
