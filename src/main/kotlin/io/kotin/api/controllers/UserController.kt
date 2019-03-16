package io.kotin.api.controllers

import io.kotin.api.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate


@RestController
class UserController {

    @Autowired
    lateinit var userService: UserService;


    // Get the user by idap
//    @GetMapping()
//    fun get(auth: Authentication) {
//        // If the AuthUserId is
//        if (auth.principal == ) return this.get();
//    }
//
    @PostMapping()
    fun create() {

    }
}

//    @PostMapping("/callback")
//    fun createUserFromAuth0(auth: Authentication, @RequestBody user: NewUser): String {
//        val userId= auth.principal
//
//        print(userId)
//
//        return userService.hellyeah()
//    }