package com.github.wenpiner.tron.block.data

import com.github.wenpiner.tron.block.data.transaction.Transaction
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
    val transaction : Transaction,
)