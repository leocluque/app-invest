package com.example.network.data.create_service

import android.os.Handler
import android.os.Looper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

const val UNAUTHORIZED_CODE = 401

class UnauthorisedInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code == UNAUTHORIZED_CODE) {
            Handler(Looper.getMainLooper()).post { UserUnauthorizedBus.setEvent() }
        }
        return response
    }
}
