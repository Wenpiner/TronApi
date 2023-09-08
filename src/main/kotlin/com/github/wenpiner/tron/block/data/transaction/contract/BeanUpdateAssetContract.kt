package com.github.wenpiner.tron.block.data.transaction.contract

import com.google.gson.annotations.SerializedName

data class BeanUpdateAssetContract(
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("new_limit")
    val newLimit: Long,
    @SerializedName("new_public_limit")
    val newPublicLimit: Long
) : BeanValue()
