package com.github.wenpiner.tron.block.data.transaction

import com.github.phish.tron.block.data.Address
import com.github.phish.tron.block.data.transaction.contract.TRC20_Transfer
import com.github.phish.tron.block.serializer.StringToAddressAdapter
import com.github.phish.tron.block.utils.toBigInt
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class TransactionInfo(
    @SerializedName("id")
    val id: String,
    @SerializedName("fee")
    val fee: Long,
    @SerializedName("blockNumber")
    val blockNumber: Long,
    @SerializedName("blockTimeStamp")
    val blockTimeStamp: Long,
    @SerializedName("contractResult")
    val contractResult: Array<String>,
    @SerializedName("contract_address")
    @JsonAdapter(value = StringToAddressAdapter::class)
    val contractAddress: Address,
    @SerializedName("receipt")
    val receipt: TransactionReceipt,
    @SerializedName("log")
    val log: Array<TransactionLog>,

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TransactionInfo) return false

        if (id != other.id) return false
        if (fee != other.fee) return false
        if (blockNumber != other.blockNumber) return false
        if (blockTimeStamp != other.blockTimeStamp) return false
        if (!contractResult.contentEquals(other.contractResult)) return false
        if (contractAddress != other.contractAddress) return false
        if (receipt != other.receipt) return false
        if (!log.contentEquals(other.log)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + fee.hashCode()
        result = 31 * result + blockNumber.hashCode()
        result = 31 * result + blockTimeStamp.hashCode()
        result = 31 * result + contractResult.contentHashCode()
        result = 31 * result + contractAddress.hashCode()
        result = 31 * result + receipt.hashCode()
        result = 31 * result + log.contentHashCode()
        return result
    }
}

// {
//        "energy_usage": 31895,
//        "energy_usage_total": 31895,
//        "net_usage": 353,
//        "result": "SUCCESS",
//        "energy_penalty_total": 17245
//    }
data class TransactionReceipt(
    @SerializedName("energy_usage")
    val energyUsage: Long,
    @SerializedName("energy_usage_total")
    val energyUsageTotal: Long,
    @SerializedName("net_usage")
    val netUsage: Long,
    @SerializedName("result")
    val result: String,
    @SerializedName("energy_penalty_total")
    val energyPenaltyTotal: Long,
)


//{
//            "address": "a614f803b6fd780986a42c78ec9c7f77e6ded13c",
//            "topics": [
//                "ddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef",
//                "0000000000000000000000006df63081a640798633d5d27152a1f2f4035c468e",
//                "000000000000000000000000fc631e982c1dbad2cdba2e2ff1af68d458cf7f2c"
//            ],
//            "data": "000000000000000000000000000000000000000000000000000000001139ca40"
//        }
data class TransactionLog(
    @SerializedName("address")
    val address: String,
    @SerializedName("topics")
    val topics: Array<String>,
    @SerializedName("data")
    val data: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TransactionLog) return false

        if (address != other.address) return false
        if (!topics.contentEquals(other.topics)) return false
        if (data != other.data) return false

        return true
    }

    override fun hashCode(): Int {
        var result = address.hashCode()
        result = 31 * result + topics.contentHashCode()
        result = 31 * result + data.hashCode()
        return result
    }

    fun functionTransfer(): TRC20_Transfer? {
        if (topics.size != 3) return null
        if (topics[0] != "ddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef") return null
        return TRC20_Transfer(
            from = Address(topics[1]),
            to = Address(topics[2]),
            value = data.toBigInt(),
            contract = Address(address),
        )
    }
}