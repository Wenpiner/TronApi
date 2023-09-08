package com.github.wenpiner.tron.block.data.transaction.contract

import com.google.gson.annotations.SerializedName

class BeanWitnessCreateContract(
    @SerializedName("url")
    val url: String,
) : BeanValue()