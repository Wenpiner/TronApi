package com.github.wenpiner.tron.block.api

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.math.BigDecimal
import java.math.BigInteger

fun Map<String, Any>.toRequestBody(): RequestBody {
    val obj = JsonObject()
    this.keys.forEach {
        // 判断是否为String、Boolean、Int、Double
        when (val value = this[it]) {
            is String -> {
                obj.addProperty(it, value)
            }

            is Boolean -> {
                obj.addProperty(it, value)
            }

            is Int -> {
                obj.addProperty(it, value)
            }

            is Double -> {
                obj.addProperty(it, value)
            }

            is BigDecimal -> {
                obj.addProperty(it, value)
            }

            is BigInteger -> {
                obj.addProperty(it, value)
            }

            else -> {
                // 其他类型，转成json字符串
                obj.add(it, JsonParser.parseString(Gson().toJson(value)))
            }
        }
    }
    return obj.toString().toRequestBody("application/json".toMediaType())
}