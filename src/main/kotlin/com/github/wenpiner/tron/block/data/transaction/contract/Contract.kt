package com.github.wenpiner.tron.block.data.transaction.contract

import com.google.gson.annotations.SerializedName

data class Contract(
    @SerializedName("type")
    val type: ContractType,
    @SerializedName("parameter")
    val parameter: Parameter
)
