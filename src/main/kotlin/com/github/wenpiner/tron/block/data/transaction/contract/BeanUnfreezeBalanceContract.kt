package com.github.wenpiner.tron.block.data.transaction.contract

import com.github.phish.tron.block.data.Address
import com.github.phish.tron.block.enums.ResourceCode
import com.github.phish.tron.block.serializer.StringToAddressAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class BeanUnfreezeBalanceContract(
    @SerializedName("resource")
    val resource: ResourceCode,
    @SerializedName("receiver_address")
    @JsonAdapter(value = StringToAddressAdapter::class)
    val receiverAddress: Address,
) : BeanValue()
