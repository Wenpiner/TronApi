package com.github.wenpiner.tron.block.data.transaction.contract

import com.github.wenpiner.tron.block.data.Address
import com.github.wenpiner.tron.block.enums.ResourceCode
import com.github.wenpiner.tron.block.serializer.StringToAddressAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class BeanFreezeBalanceContract(
    @SerializedName("frozen_balance")
    val frozenBalance: Long,
    @SerializedName("frozen_duration")
    val frozenDuration: Long,
    @SerializedName("resource")
    val resource: ResourceCode,
    @SerializedName("receiver_address")
    @JsonAdapter(value = StringToAddressAdapter::class)
    val receiverAddress: Address,
) : com.github.wenpiner.tron.block.data.transaction.contract.BeanValue()
