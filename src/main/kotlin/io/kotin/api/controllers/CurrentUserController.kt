package io.kotin.api.controllers

import io.kotin.api.models.User
import io.kotin.api.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/user/current")
class CurrentUserController {

    @Autowired
    lateinit var userService: UserService;

    @GetMapping()
    fun get(authentication: Authentication): User {
        return this.userService.getCurrent(authentication)
    }

    @PutMapping()
    fun update(authentication: Authentication, @RequestBody user: User): User {
        return this.userService.updateCurrent(authentication, user);
    }
}