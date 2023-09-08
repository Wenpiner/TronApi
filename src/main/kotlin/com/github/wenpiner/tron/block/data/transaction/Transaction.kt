package com.github.wenpiner.tron.block.data.transaction

import com.google.gson.annotations.SerializedName

data class Transaction(
    @SerializedName("signature")
    val signature: List<String>,
    @SerializedName("txID")
    val txId: String,
    @SerializedName("raw_data_hex")
    val rawDataHex: String,
    @SerializedName("ret")
    val ret: List<Ret>,
    @SerializedName("raw_data")
    val rawData: RawData,
)
