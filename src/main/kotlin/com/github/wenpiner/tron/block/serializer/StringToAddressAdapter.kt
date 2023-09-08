package com.github.wenpiner.tron.block.serializer

import com.github.phish.tron.block.data.Address
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException


class StringToAddressAdapter : TypeAdapter<Address>() {
    @Throws(IOException::class)
    override fun write(jsonWriter: JsonWriter, address: Address) {
        jsonWriter.value(address.toString())
    }

    @Throws(IOException::class)
    override fun read(jsonReader: JsonReader): Address {
        val s = jsonReader.nextString()
        return Address(s)
    }
}

