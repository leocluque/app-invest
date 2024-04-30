package com.example.network.create_service

import com.example.network.data.create_service.ServiceGenerator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

class ServiceGeneratorTest {

    @Test
    fun testCreateServiceWithoutInterceptors() {
        val baseUrl = "http://example.com/"
        val service = ServiceGenerator.createService(TestService::class.java, null, baseUrl)
        assertEquals(baseUrl, service.baseUrl().request().url.toString())
    }

    @Test
    fun testCreateServiceWithInterceptors() {
        val baseUrl = "http://example.com/"
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val service = ServiceGenerator.createService(TestService::class.java, listOf(interceptor), baseUrl)
        assertEquals(baseUrl, service.baseUrl().request().url.toString())
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val interceptors = client.interceptors
        assertEquals(1, interceptors.size)
        assertEquals(interceptor, interceptors[0])
    }

    @Test
    fun testCreateServiceBaseUrl() {
        val baseUrl = "http://example.com/"
        val service = ServiceGenerator.createService(TestService::class.java, null, baseUrl)
        assertEquals(baseUrl, service.baseUrl().request().url.toString())
    }
}

interface TestService {
    @GET
    fun baseUrl(@Url url: String = ""): Call<String>
}
