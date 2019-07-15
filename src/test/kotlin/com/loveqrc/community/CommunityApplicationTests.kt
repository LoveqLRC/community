package com.loveqrc.community

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CommunityApplicationTests {
    @Value("\${github.client_id}")
    lateinit var clientID: String
    @Test
    fun contextLoads() {
        println("-------$clientID ---------")
    }

}
