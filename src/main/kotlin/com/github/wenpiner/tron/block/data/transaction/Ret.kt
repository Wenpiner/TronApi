package com.github.wenpiner.tron.block.data.transaction

import com.google.gson.annotations.SerializedName







data class Ret(
    @SerializedName("contractRet")
    val contractRet: com.github.wenpiner.tron.block.data.transaction.ContractRet,
    @SerializedName("ret")
    val ret: com.github.wenpiner.tron.block.data.transaction.RetResult
)