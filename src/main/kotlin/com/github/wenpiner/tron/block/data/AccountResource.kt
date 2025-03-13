package com.github.wenpiner.tron.block.data

import com.google.gson.annotations.SerializedName

data class AccountResource(
    @SerializedName("freeNetUsed")
    val freeNetUsed: Long,
    @SerializedName("freeNetLimit")
    val freeNetLimit: Long,
    @SerializedName("NetUsed")
    val netUsed: Long,
    @SerializedName("NetLimit")
    val netLimit: Long,
    @SerializedName("TotalNetLimit")
    val totalNetLimit: Long,
    @SerializedName("TotalNetWeight")
    val totalNetWeight: Long,
    @SerializedName("totalTronPowerWeight")
    val totalTronPowerWeight: Long,
    @SerializedName("tronPowerLimit")
    val tronPowerLimit: Long,
    @SerializedName("tronPowerUsed")
    val tronPowerUsed: Long,
    @SerializedName("EnergyUsed")
    val energyUsed: Long,
    @SerializedName("EnergyLimit")
    val energyLimit: Long,
    @SerializedName("TotalEnergyLimit")
    val totalEnergyLimit: Long,
    @SerializedName("TotalEnergyWeight")
    val totalEnergyWeight: Long,
    @SerializedName("assetNetUsed")
    val assetNetUsed: Map<String, Long>,
    @SerializedName("assetNetLimit")
    val assetNetLimit: Map<String, Long>
)