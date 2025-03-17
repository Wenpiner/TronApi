package com.github.wenpiner.tron.block.serializer

import com.github.wenpiner.tron.block.data.transaction.Transaction
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException
import kotlin.jvm.Throws

data class Struct<T>(
    val value: T
)

class StringToStructAdapter : TypeAdapter<Struct<Transaction>>() {

    @Throws(IOException::class)
    override fun write(out: JsonWriter?, value: Struct<Transaction>?) {
        // 将Struct里面的value转换为json数据
        val g = Gson()
        val s = g.toJson(value?.value)
        out?.value(s)
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader?): Struct<Transaction> {
        val s = `in`?.nextString()
        // 将json数据转换为Struct里面的value
        val g = Gson()
        val transaction = g.fromJson(s, Transaction::class.java)
        return Struct(transaction)
    }

}