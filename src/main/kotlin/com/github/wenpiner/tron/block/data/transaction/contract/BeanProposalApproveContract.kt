package com.github.wenpiner.tron.block.data.transaction.contract

import com.google.gson.annotations.SerializedName

data class BeanProposalApproveContract(
    @SerializedName("proposal_id")
    val proposalId: Long,
    @SerializedName("is_add_approval")
    val isAddApproval: Boolean,
) : com.github.phish.tron.block.data.transaction.contract.BeanValue()
