package com.github.wenpiner.tron.block.data.transaction.contract

import com.github.wenpiner.tron.block.data.Address
import com.github.wenpiner.tron.block.serializer.StringToAddressAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class BeanParticipateAssetIssueContract(
    @SerializedName("asset_name")
    val assetName: String,
    @SerializedName("amount")
    val amount: Long,
    @SerializedName("to_address")
    @JsonAdapter(value = StringToAddressAdapter::class)
    val toAddress: Address,
) : com.github.wenpiner.tron.block.data.transaction.contract.BeanValue()
