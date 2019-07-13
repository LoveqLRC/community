package com.loveqrc.community.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AuthorizeController {

    @GetMapping
    fun callback(@RequestParam(name="code") code:String,
                 @RequestParam(name="state") state:String):String{
        return "/index"
    }
}