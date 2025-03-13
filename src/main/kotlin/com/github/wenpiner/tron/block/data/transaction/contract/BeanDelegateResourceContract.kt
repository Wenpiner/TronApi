package com.github.wenpiner.tron.block.data.transaction.contract

import com.github.wenpiner.tron.block.data.Address
import com.github.wenpiner.tron.block.enums.ResourceCode
import com.github.wenpiner.tron.block.serializer.StringToAddressAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class BeanDelegateResourceContract(
    @SerializedName("receiver_address")
    @JsonAdapter(value = StringToAddressAdapter::class)
    val receiverAddress: Address,
    @SerializedName("balance")
    val balance: Long,
    @SerializedName("resource")
    val resource: ResourceCode,

    ) : com.github.wenpiner.tron.block.data.transaction.contract.BeanValue()
