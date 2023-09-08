package com.github.wenpiner.tron.block.data.transaction.contract

import com.github.phish.tron.block.data.Address
import com.github.phish.tron.block.enums.AccountType
import com.github.phish.tron.block.serializer.StringToAddressAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class BeanAccountCreateContract(
    @SerializedName("account_address")
    @JsonAdapter(value = StringToAddressAdapter::class)
    val accountAddress: Address,
    @SerializedName("type")
    val type: AccountType,
) : com.github.phish.tron.block.data.transaction.contract.BeanValue()
