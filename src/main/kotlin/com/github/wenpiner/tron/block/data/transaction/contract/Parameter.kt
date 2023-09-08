package com.github.wenpiner.tron.block.data.transaction.contract

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class Parameter(
    @SerializedName("type_url")
    val typeUrl: String,
    @SerializedName("value")
    private val rawVal: JsonObject
) {
    fun <T : BeanValue> getValue(contractType: ContractType): T {
        return Gson().fromJson<T>(rawVal, contractType.clazz)
    }

    /**
     * 获取合约类型，如果没有则抛出异常
     */
    fun getContractType(): ContractType? {
        return ContractType.values().find { it.str == typeUrl }
    }
}