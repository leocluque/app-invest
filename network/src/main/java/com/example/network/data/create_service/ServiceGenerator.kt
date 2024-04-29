package com.example.network.data.create_service

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenerator {

    companion object {
        fun <S> createService(
            serviceClass: Class<S>, interceptors: List<Interceptor>? = null, url: String
        ): S {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

            httpClient.connectTimeout(50, TimeUnit.SECONDS)
            httpClient.readTimeout(50, TimeUnit.SECONDS)
            httpClient.writeTimeout(50, TimeUnit.SECONDS)

            interceptors?.let {
                for (interceptor in interceptors) {
                    httpClient.addInterceptor(interceptor)
                }
            }
            retrofit.client(httpClient.build())
            return retrofit.build().create(serviceClass)
        }
    }
}
