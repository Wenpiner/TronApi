package com.github.wenpiner.tron.block.data.transaction.contract

import com.google.gson.annotations.SerializedName

data class BeanAssetIssueContract(
    @SerializedName("name")
    val name: String,
    @SerializedName("total_supply")
    val totalSupply: Long,
    @SerializedName("frozen_supply")
    val frozenSupply: List<com.github.wenpiner.tron.block.data.transaction.contract.FrozenSupply>,
    @SerializedName("trx_num")
    val trxNum: Long,
    @SerializedName("precision")
    val precision: Int,
    @SerializedName("num")
    val num: Int,
    @SerializedName("start_time")
    val startTime: Long,
    @SerializedName("end_time")
    val endTime: Long,
    @SerializedName("order")
    val order: Long,
    @SerializedName("vote_score")
    val voteScore: Long,
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("free_asset_net_limit")
    val freeAssetNetLimit: Long,
    @SerializedName("public_free_asset_net_limit")
    val publicFreeAssetNetLimit: Long,
    @SerializedName("public_free_asset_net_usage")
    val publicFreeAssetNetUsage: Long,
    @SerializedName("public_latest_free_net_time")
    val publicLatestFreeNetTime: Long,
) : com.github.wenpiner.tron.block.data.transaction.contract.BeanValue()

data class FrozenSupply(
    @SerializedName("frozen_amount")
    val frozenAmount: Long,
    @SerializedName("frozen_days")
    val frozenDays: Long,
) : com.github.wenpiner.tron.block.data.transaction.contract.BeanValue()
