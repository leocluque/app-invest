package com.example.network.data.create_service

import com.example.network.data.local.PreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

private const val RESPONSE_HEADER_COOKIE = "Set-Cookie"

class ReceivedCookieInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())

        val cookie = originalResponse.headers[RESPONSE_HEADER_COOKIE] ?: ""
        try {
            if (cookie.isNotEmpty() && PreferencesHelper.sessionCookie != cookie) {
                PreferencesHelper.sessionCookie = cookie
            }
        } catch (e: NullPointerException) {
            // Do Nothing
        }
        return originalResponse
    }
}
