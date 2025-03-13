package com.github.wenpiner.tron.block.data

import com.github.wenpiner.tron.block.data.transaction.Transaction
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("result")
    val result: Boolean,
)

data class TriggerSmartContract (
    @SerializedName("result")
    val result: Result,
    @SerializedName("energy_used")
    val energyUsed: Long,
    @SerializedName("constant_result")
    val constantResult: Array<String>,
    @SerializedName("transaction")
    val transaction: Transaction,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TriggerSmartContract

        if (energyUsed != other.energyUsed) return false
        if (result != other.result) return false
        if (!constantResult.contentEquals(other.constantResult)) return false
        if (transaction != other.transaction) return false

        return true
    }

    override fun hashCode(): Int {
        var result1 = energyUsed.hashCode()
        result1 = 31 * result1 + result.hashCode()
        result1 = 31 * result1 + constantResult.contentHashCode()
        result1 = 31 * result1 + transaction.hashCode()
        return result1
    }
}