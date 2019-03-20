package io.kotin.api.controllers

import io.kotin.api.models.NewUser
import io.kotin.api.models.User
import io.kotin.api.respositories.UserRepository
import io.kotin.api.services.UserService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate


@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var userService: UserService;

    @GetMapping("/{userId}")
    fun get(@PathVariable(value="userId") userId: String): User {
        return this.userService.get(userId)
    }

    @PostMapping()
    fun create(@RequestBody newUser: NewUser): User {
        return this.userService.create(newUser)
    }

    @PutMapping()
    fun update(@RequestBody user: User): User {
        return this.userService.update(user);
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