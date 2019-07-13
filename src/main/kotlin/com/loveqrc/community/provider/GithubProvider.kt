package com.loveqrc.community.provider

import com.loveqrc.community.dto.AccessTokenDto
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.springframework.stereotype.Component

@Component
class GithubProvider {
    fun getAccessToken(accessTokenDto: AccessTokenDto): String? {

        val JSON = "application/json; charset=utf-8".toMediaType()
        val client = OkHttpClient()
        val body = RequestBody.create(JSON, "")
        val request = Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build()
        try {
            val response = client.newCall(request).execute()
            return response.body.toString()
        } catch (e: Exception) {

        }
        return null
//
//        OkHttpClient client = new OkHttpClient();
//
//        String post(String url, String json) throws IOException {
//            RequestBody body = RequestBody.create(JSON, json);
//            Request request = new Request.Builder()
//                    .url(url)
//                    .post(body)
//                    .build();
//            try (Response response = client.newCall(request).execute()) {
//                return response.body().string();
//            }
    }
}