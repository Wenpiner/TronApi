package com.github.wenpiner.tron.block.data.transaction.contract

import com.google.gson.annotations.SerializedName

data class BeanWitnessUpdateContract(
    @SerializedName("update_url")
    val updateUrl: String,
) : BeanValue()
