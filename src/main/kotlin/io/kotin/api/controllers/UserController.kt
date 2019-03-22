package io.kotin.api.controllers

import io.kotin.api.models.NewUser
import io.kotin.api.models.User
import io.kotin.api.models.UserBase
import io.kotin.api.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var userService: UserService;

    @GetMapping("/{userId}")
    fun get(@PathVariable(value = "userId") userId: String): UserBase {
        return this.userService.get(userId)
    }

    @PostMapping()
    fun create(@RequestBody newUser: NewUser): User {
        return this.userService.create(newUser)
    }
}