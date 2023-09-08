package com.github.wenpiner.tron.block.data.transaction.contract

import com.github.phish.tron.block.data.Address
import com.github.phish.tron.block.serializer.StringToAddressAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class BeanVoteAssetContract(
    @SerializedName("vote_address")
    @JsonAdapter(value = StringToAddressAdapter::class)
    val voteAddress: Address,
    @SerializedName("support")
    val support: Boolean,
    @SerializedName("count")
    val count: Long,
) : BeanValue()