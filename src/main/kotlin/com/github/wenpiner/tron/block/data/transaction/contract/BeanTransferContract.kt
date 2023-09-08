package com.github.wenpiner.tron.block.data.transaction.contract

import com.github.phish.tron.block.data.Address
import com.github.phish.tron.block.serializer.StringToAddressAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.math.RoundingMode

data class BeanTransferContract(
    @SerializedName("to_address")
    @JsonAdapter(value = StringToAddressAdapter::class)
    val toAddress: Address,
    @SerializedName("amount")
    val amount: Long,
) : com.github.phish.tron.block.data.transaction.contract.BeanValue() {
    fun toTrxAmount(): BigDecimal {
        return toAmount(6)
    }

    fun toAmount(decimal: Int): BigDecimal {
        // 非0判断
        if (amount == 0L) {
            return BigDecimal.ZERO
        }
        return BigDecimal(amount).divide(BigDecimal.TEN.pow(decimal), decimal, RoundingMode.DOWN)
    }
}