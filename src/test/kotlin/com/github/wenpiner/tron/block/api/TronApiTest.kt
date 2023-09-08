package com.github.wenpiner.tron.block.api

import com.github.phish.tron.block.data.Block
import com.github.phish.tron.block.transaction.contract.ContractType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class TronApiTest {

    private val tronApi = TronApi()

    @Test
    fun getBlockByIdOrNum() {
        val result = tronApi.getBlockByIdOrNum("54492401", true)
        assertTrue(result.data != null && result.code == 200, result.message)
        printInfo(result)
    }

    private fun printInfo(result: Result<Block>) {
        println("BlockNum: ${result.data!!.blockHeader.rawData.number}")
        println("BlockHash: ${result.data!!.blockId}")
        println("BlockTime: ${result.data!!.blockHeader.rawData.timestamp}")
        println("BlockWitness: ${result.data!!.blockHeader.rawData.witnessAddress}")
        println("BlockParentHash: ${result.data!!.blockHeader.rawData.parentHash}")
        println("BlockTxTrieRoot: ${result.data!!.blockHeader.rawData.txTrieRoot}")
        println("BlockTxCount: ${result.data!!.transactions.size}")

        // 打印交易信息
        result.data!!.transactions.forEach {
            println("TxId: ${it.txId}")
            // 判断是否为TRC20
            it.rawData.contract.forEach { it2 ->
                val contractType = it2.parameter.getContractType()
                if (contractType != null) {
                    if (contractType == ContractType.TriggerSmartContract) {
                        val triggerSmartContract: com.github.phish.tron.block.data.transaction.contract.BeanTriggerSmartContract = it2.parameter.getValue(contractType)
                        println("From: ${triggerSmartContract.ownerAddress}")
                        println("Contract: ${triggerSmartContract.contractAddress}")
                        val functionTransfer = triggerSmartContract.functionTransfer()
                        if (functionTransfer != null) {
                            println("To: ${functionTransfer.to}")
                            println("Amount: ${functionTransfer.value}")
                        }
                    }
                } else {
                    println("ContractType is null")
                }
            }
        }
    }

    @Test
    fun getBlockByNum() {
        val result = tronApi.getBlockByNum(54492401, true)
        assertTrue(result.data != null && result.code == 200, result.message)
        printInfo(result)
    }


}