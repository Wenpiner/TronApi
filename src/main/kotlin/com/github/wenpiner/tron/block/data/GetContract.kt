package com.github.wenpiner.tron.block.data

import com.google.gson.annotations.SerializedName

data class ContractInfo(
    @SerializedName("bytecode")
    val bytecode: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_address")
    val originAddress: String,
    @SerializedName("abi")
    val abi: Abi,
    @SerializedName("origin_energy_limit")
    val originEnergyLimit: Long,
    @SerializedName("contract_address")
    val contractAddress: String,
    @SerializedName("code_hash")
    val codeHash: String
)

data class Abi(
    @SerializedName("entrys")
    val entrys: List<Entry>
)

data class Entry(
    @SerializedName("stateMutability")
    val stateMutability: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("inputs")
    val inputs: List<Input>? = null,
    @SerializedName("outputs")
    val outputs: List<Output>? = null,
    @SerializedName("constant")
    val constant: Boolean? = null,
    @SerializedName("name")
    val name: String? = null
)

data class Input(
    @SerializedName("indexed")
    val indexed: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
)

data class Output(
    @SerializedName("type")
    val type: String
)