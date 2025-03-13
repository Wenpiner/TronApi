package com.github.wenpiner.tron.block.data.transaction.contract

import com.github.wenpiner.tron.block.data.Address
import com.github.wenpiner.tron.block.serializer.StringToAddressAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

open class BeanValue {
    @SerializedName("owner_address")
    @JsonAdapter(value = StringToAddressAdapter::class)
    lateinit var ownerAddress: Address;
}