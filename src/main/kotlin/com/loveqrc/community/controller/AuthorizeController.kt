package com.loveqrc.community.controller

import com.loveqrc.community.dto.AccessTokenDto
import com.loveqrc.community.provider.GithubProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AuthorizeController {

    @Autowired
    lateinit var githubProvider: GithubProvider

    @Value("\${github.client_id}")
    lateinit var clientID: String

    @Value("\${github.client_secret}")
    lateinit var clientSecret: String

    @Value("\${github.redirect_uri}")
    lateinit var redirectUri: String


    @GetMapping
    fun callback(@RequestParam(name = "code") code: String,
                 @RequestParam(name = "state") state: String
    ): String {
        val tokenDto = AccessTokenDto(clientID,
                clientSecret,
                code,
                redirectUri,
                state)

        val accessToken = githubProvider.getAccessToken(tokenDto)
        if (accessToken != null) {
            val githubUser = githubProvider.getUser(accessToken)
            println(githubUser)
            return if (githubUser != null) {
                "redirect:/"
            } else {
                "redirect:/"
            }
        }
        return "/index"
    }
}