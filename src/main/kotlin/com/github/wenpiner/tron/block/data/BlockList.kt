package com.github.wenpiner.tron.block.data

import com.google.gson.annotations.SerializedName

data class BlockList(
    @SerializedName("block")
    val block: List<Block>
)