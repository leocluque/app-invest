package com.example.network.data.create_service

import com.example.network.data.local.PreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AddCookieInterceptor : Interceptor {
    private val REQUEST_HEADER_COOKIE = "Set-Cookie"
    private val TOKEN = "Authorization"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val id = PreferencesHelper.sessionCookie
        val token = PreferencesHelper.basicAuth
        val original = chain.request()
        var request = original

        // Only adds if there's a cookie
        if (!token.isEmpty()) {
            request = original.newBuilder()
                .addHeader(REQUEST_HEADER_COOKIE, id)
                .addHeader(TOKEN, "Bearer $token")
                .method(original.method, original.body)
                .build()
        }
        return chain.proceed(request)
    }
}
