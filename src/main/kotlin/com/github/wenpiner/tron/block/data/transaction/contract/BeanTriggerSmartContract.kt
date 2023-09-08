package com.github.wenpiner.tron.block.data.transaction.contract

import com.github.phish.tron.block.data.Address
import com.github.phish.tron.block.serializer.StringToAddressAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class BeanTriggerSmartContract(
    @SerializedName("contract_address")
    @JsonAdapter(value = StringToAddressAdapter::class)
    val contractAddress: Address,
    @SerializedName("data")
    val data: String,
) : com.github.phish.tron.block.data.transaction.contract.BeanValue() {
    private fun isTransfer(): Boolean {
        return data.startsWith("a9059cbb") && data.length >= 136
    }

    private fun isTransferFrom(): Boolean {
        return data.startsWith("23b872dd") && data.length >= 136
    }


    fun functionTransfer(): com.github.phish.tron.block.data.transaction.contract.TRC20_Transfer? {
        if (!isTransfer() && !isTransferFrom()) {
            return null
        }
        val from = ownerAddress
        val to = Address(data.substring(8, 72))
        val value = data.substring(72, 72 + 64)
        return com.github.phish.tron.block.data.transaction.contract.TRC20_Transfer(
            from,
            to,
            contractAddress,
            BigInteger(value, 16)
        )
    }
}

data class TRC20_Transfer(
    val from: Address,
    val to: Address,
    val contract: Address,
    val value: BigInteger,
)