package com.github.wenpiner.tron.block.data

import com.google.gson.annotations.SerializedName

data class GetAccount(
    @SerializedName("address")
    val address: String,
    @SerializedName("balance")
    val balance: Long,
    @SerializedName("net_usage")
    val netUsage: Long,
    @SerializedName("create_time")
    val createTime: Long,
    @SerializedName("latest_opration_time")
    val latestOperationTime: Long,
    @SerializedName("latest_consume_time")
    val latestConsumeTime: Long,
    @SerializedName("latest_consume_free_time")
    val latestConsumeFreeTime: Long,
    @SerializedName("net_window_size")
    val netWindowSize: Long,
    @SerializedName("net_window_optimized")
    val netWindowOptimized: Boolean,
    @SerializedName("account_resource")
    val accountResource: GetAccountResource,
    @SerializedName("owner_permission")
    val ownerPermission: OwnerPermission,
    @SerializedName("active_permission")
    val activePermission: List<ActivePermission>,
    @SerializedName("frozenV2")
    val frozenV2: List<FrozenV2>,
    @SerializedName("acquired_delegated_frozenV2_balance_for_bandwidth")
    val acquiredDelegatedFrozenV2BalanceForBandwidth: Long,
    @SerializedName("assetV2")
    val assetV2: List<AssetV2>,
    @SerializedName("free_asset_net_usageV2")
    val freeAssetNetUsageV2: List<FreeAssetNetUsageV2>,
    @SerializedName("asset_optimized")
    val assetOptimized: Boolean
)

data class GetAccountResource(
    @SerializedName("energy_usage")
    val energyUsage: Long,
    @SerializedName("latest_consume_time_for_energy")
    val latestConsumeTimeForEnergy: Long,
    @SerializedName("energy_window_size")
    val energyWindowSize: Long,
    @SerializedName("acquired_delegated_frozenV2_balance_for_energy")
    val acquiredDelegatedFrozenV2BalanceForEnergy: Long,
    @SerializedName("energy_window_optimized")
    val energyWindowOptimized: Boolean
)

data class OwnerPermission(
    @SerializedName("permission_name")
    val permissionName: String,
    @SerializedName("threshold")
    val threshold: Int,
    @SerializedName("keys")
    val keys: List<Key>
)

data class ActivePermission(
    @SerializedName("type")
    val type: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("permission_name")
    val permissionName: String,
    @SerializedName("threshold")
    val threshold: Int,
    @SerializedName("operations")
    val operations: String,
    @SerializedName("keys")
    val keys: List<Key>
)

data class Key(
    @SerializedName("address")
    val address: String,
    @SerializedName("weight")
    val weight: Int
)

data class FrozenV2(
    @SerializedName("type")
    val type: String? = null
)

data class AssetV2(
    @SerializedName("key")
    val key: String,
    @SerializedName("value")
    val value: Long
)

data class FreeAssetNetUsageV2(
    @SerializedName("key")
    val key: String,
    @SerializedName("value")
    val value: Long
)