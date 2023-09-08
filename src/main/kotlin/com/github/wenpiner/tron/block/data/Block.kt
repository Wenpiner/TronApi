package com.github.wenpiner.tron.block.data

import com.github.phish.tron.block.data.BlockHeader
import com.google.gson.annotations.SerializedName

data class Block(
    @SerializedName("blockID")
    val blockId: String,
    @SerializedName("block_header")
    val blockHeader: BlockHeader,
    @SerializedName("transactions")
    val transactions: List<com.github.phish.tron.block.data.transaction.Transaction>
)
