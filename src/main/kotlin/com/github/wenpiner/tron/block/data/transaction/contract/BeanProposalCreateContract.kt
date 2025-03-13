package com.github.wenpiner.tron.block.data.transaction.contract

import com.google.gson.annotations.SerializedName

data class BeanProposalCreateContract(
    @SerializedName("parameters")
    val parameters: Map<Long, Long>,
) : com.github.wenpiner.tron.block.data.transaction.contract.BeanValue()
