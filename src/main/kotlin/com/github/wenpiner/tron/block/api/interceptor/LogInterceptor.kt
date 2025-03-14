package com.github.wenpiner.tron.block.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class LogInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        println("request: ${request.url}")
        // 打印body
        println("request body: ${request.body?.toString()}")
        val response = chain.proceed(request)
//        println("response: ${response.body?.string()}")
        return response
    }
}