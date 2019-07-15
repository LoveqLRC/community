package com.loveqrc.community.provider

import com.loveqrc.community.dto.AccessTokenDto
import com.loveqrc.community.dto.GithubUser
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.springframework.stereotype.Component
import com.alibaba.fastjson.JSON
import com.google.gson.Gson


@Component
class GithubProvider {
    fun getAccessToken(accessTokenDto: AccessTokenDto): String? {

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val client = OkHttpClient()
        val body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto))
        val request = Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build()
        try {
            val response = client.newCall(request).execute()
            val responseStr = response.body!!.string()

            return responseStr.split("&")[0].split("=")[1]
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
            val gson = Gson()
//            var fromJson =
            return gson.fromJson(str, GithubUser::class.java)
        } catch (e: Exception) {
        }
        return null
    }
}
//{"login":"LoveqLRC","id":13888325,"node_id":"MDQ6VXNlcjEzODg4MzI1","avatar_url":"https://avatars1.githubusercontent.com/u/13888325?v=4","gravatar_id":"","url":"https://api.github.com/users/LoveqLRC","html_url":"https://github.com/LoveqLRC","followers_url":"https://api.github.com/users/LoveqLRC/followers","following_url":"https://api.github.com/users/LoveqLRC/following{/other_user}","gists_url":"https://api.github.com/users/LoveqLRC/gists{/gist_id}","starred_url":"https://api.github.com/users/LoveqLRC/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/LoveqLRC/subscriptions","organizations_url":"https://api.github.com/users/LoveqLRC/orgs","repos_url":"https://api.github.com/users/LoveqLRC/repos","events_url":"https://api.github.com/users/LoveqLRC/events{/privacy}","received_events_url":"https://api.github.com/users/LoveqLRC/received_events","type":"User","site_admin":false,"name":null,"company":null,"blog":"","location":null,"email":null,"hireable":null,"bio":null,"public_repos":77,"public_gists":0,"followers":3,"following":35,"created_at":"2015-08-20T14:16:19Z","updated_at":"2019-05-16T07:59:35Z","private_gists":0,"total_private_repos":0,"owned_private_repos":0,"disk_usage":198643,"collaborators":0,"two_factor_authentication":false,"plan":{"name":"free","space":976562499,"collaborators":0,"private_repos":10000}}