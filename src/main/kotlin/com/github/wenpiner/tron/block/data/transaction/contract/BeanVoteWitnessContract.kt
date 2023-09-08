package com.github.wenpiner.tron.block.data.transaction.contract

import com.github.phish.tron.block.data.Address
import com.github.phish.tron.block.serializer.StringToAddressAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class BeanVoteWitnessContract(
    @SerializedName("votes")
    val votes: List<Vote>,
    @SerializedName("support")
    val support: Boolean,
) : BeanValue()

data class Vote(
    @SerializedName("vote_address")
    @JsonAdapter(value = StringToAddressAdapter::class)
    val voteAddress: Address,
    @SerializedName("vote_count")
    val voteCount: Long,
)