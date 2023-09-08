package com.github.wenpiner.tron.block.data.transaction

import com.github.phish.tron.block.transaction.contract.Contract
import com.google.gson.annotations.SerializedName

data class RawData(
    @SerializedName("ref_block_bytes")
    val refBlockBytesHex: String,
    @SerializedName("ref_block_hash")
    val refBlockHashHex: String,
    @SerializedName("expiration")
    val expiration: Long,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("fee_limit")
    val feeLimit: Long,
    @SerializedName("contract")
    val contract: List<Contract>,
    @SerializedName("data")
    val data: String? = null,
)