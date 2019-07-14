package com.loveqrc.community.provider

import com.loveqrc.community.dto.AccessTokenDto
import com.loveqrc.community.dto.GithubUser
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.springframework.stereotype.Component
import com.alibaba.fastjson.JSON


@Component
class GithubProvider {
    fun getAccessToken(accessTokenDto: AccessTokenDto): String? {

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val client = OkHttpClient()
        val body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDto))
        val request = Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build()
        try {
            val response = client.newCall(request).execute()
            val responseStr = response.body!!.string()
            println(responseStr)
            return responseStr
        } catch (e: Exception) {

        }
        return null
    }

    fun getUser(accessToken: String): GithubUser? {
        val httpClient = OkHttpClient()
        val request = Request.Builder()
                .url("https://api.github.com/user?access_token=$accessToken")
                .build()
        try {
            val response = httpClient.newCall(request).execute()
            val str = response.body!!.string()
            return JSON.parseObject(str, GithubUser::class.java)
        } catch (e: Exception) {
        }
        return null
    }
}