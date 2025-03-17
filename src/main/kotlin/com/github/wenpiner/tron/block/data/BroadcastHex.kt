package com.github.wenpiner.tron.block.data

import com.github.wenpiner.tron.block.serializer.StringToAddressAdapter
import com.github.wenpiner.tron.block.serializer.StringToStructAdapter
import com.github.wenpiner.tron.block.serializer.Struct
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class BroadcastHex(
    @SerializedName("result")
    val result : Boolean,
    @SerializedName("txid")
    val txId : String,
    @SerializedName("code")
    val code : String,
    @SerializedName("message")
    val message : String,
    @SerializedName("transaction")
    @JsonAdapter(value = StringToStructAdapter::class)
    val transaction : Struct<Transaction>,
)

data class Transaction(
    @SerializedName("signature")
    val signature : List<String>,
    @SerializedName("raw_data")
    val rawData : BroadcastRawData,
)

data class BroadcastRawData(
    @SerializedName("ref_block_bytes")
    val refBlockBytesHex : String,
    @SerializedName("ref_block_hash")
    val refBlockHashHex : String,
    @SerializedName("expiration")
    val expiration : Long,
    @SerializedName("timestamp")
    val timestamp : Long,
    @SerializedName("contract")
    val contract : List<Contract>,
)

data class Contract(
    @SerializedName("parameter")
    val parameter : Parameter,
    @SerializedName("type")
    val type : String,
)

data class Parameter(
    @SerializedName("value")
    val value : String,
    @SerializedName("type_url")
    val typeUrl : String,
)