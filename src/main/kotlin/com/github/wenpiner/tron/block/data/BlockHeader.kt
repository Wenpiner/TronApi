package com.github.wenpiner.tron.block.data

import com.github.phish.tron.block.serializer.StringToAddressAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class BlockHeader(
    @SerializedName("witness_signature")
    val witnessSignature: String,
    @SerializedName("raw_data")
    val rawData: BlockRawData,
)

data class BlockRawData(
    @SerializedName("number")
    val number: Long,
    @SerializedName("txTrieRoot")
    val txTrieRoot: String,
    @SerializedName("witness_address")
    @JsonAdapter(value = StringToAddressAdapter::class)
    val witnessAddress: Address,
    @SerializedName("parentHash")
    val parentHash: String,
    @SerializedName("version")
    val version: Int,
    @SerializedName("timestamp")
    val timestamp: Long,
)