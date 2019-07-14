package com.loveqrc.community.controller

import com.loveqrc.community.dto.AccessTokenDto
import com.loveqrc.community.provider.GithubProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AuthorizeController {

    @Autowired
    lateinit var githubProvider: GithubProvider

    @GetMapping
    fun callback(@RequestParam(name = "code") code: String,
                 @RequestParam(name = "state") state: String): String {
        val tokenDto = AccessTokenDto("7b73765abcc2e93ac2c0",
                "cfa97368de2a31a4db2e9456bab28f5f4a268ff9",
                code,
                "http://localhost:8080/callback",
                state)

        val accessToken = githubProvider.getAccessToken(tokenDto)
        if (accessToken != null) {
            val githubUser = githubProvider.getUser(accessToken)
            println(githubUser)
        }
        return "/index"
    }
}