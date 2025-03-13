package com.github.wenpiner.tron.block.data.transaction.contract

import com.google.gson.annotations.SerializedName

data class BeanProposalDeleteContract(
    @SerializedName("proposal_id")
    val proposalId: Long,
) : com.github.wenpiner.tron.block.data.transaction.contract.BeanValue()
