package com.github.wenpiner.tron.block.data.transaction.contract

import com.google.gson.annotations.SerializedName

data class BeanAccountUpdateContract(
    @SerializedName("account_name")
    val accountName: String,
) : com.github.wenpiner.tron.block.data.transaction.contract.BeanValue()