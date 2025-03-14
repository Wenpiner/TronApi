package com.github.wenpiner.tron.block.data

import com.github.wenpiner.tron.block.data.transaction.contract.Contract
import com.google.gson.annotations.SerializedName

data class BroadcastTransaction(
    @SerializedName("result")
    val result: Boolean,
    @SerializedName("txid")
    val txId: String,
    @SerializedName("code")
    val code:String,
    @SerializedName("message")
    val message: String,
)


data class RawData(
    @SerializedName("ref_block_bytes")
    val refBlockBytesHex: String,
    @SerializedName("ref_block_hash")
    val refBlockHashHex: String,
    @SerializedName("expiration")
    val expiration: Long,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("contract")
    val contract: List<Contract>,
)

data class Request(
    @SerializedName("txID")
    val txId: String,
    @SerializedName("visible")
    val visible: Boolean,
    @SerializedName("raw_data_hex")
    val rawDataHex: String,
    @SerializedName("signature")
    val signature: List<String>,
    @SerializedName("raw_data")
    val rawData: RawData,
)