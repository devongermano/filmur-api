package io.kotin.api.controllers

import io.kotin.api.models.Channel
import io.kotin.api.models.NewChannel
import io.kotin.api.services.ChannelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/channel")
class ChannelController {

    @Autowired
    lateinit var channelService: ChannelService;

    @PostMapping()
    fun create(@RequestBody newChannel: NewChannel) {
//        return this.channelService.create(newChannel)
    }
}