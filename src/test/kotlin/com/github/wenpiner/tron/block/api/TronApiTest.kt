package com.github.wenpiner.tron.block.api

import com.github.wenpiner.tron.block.data.Block
import com.github.wenpiner.tron.block.data.transaction.contract.ContractType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class TronApiTest {

    private val tronApi = TronApi(baseUrls = arrayOf("https://api.trongrid.io"))

    @Test
    fun getBlockByIdOrNum() {
        val result = tronApi.getBlockById("202121", true)
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
                        val triggerSmartContract: com.github.wenpiner.tron.block.data.transaction.contract.BeanTriggerSmartContract =
                            it2.parameter.getValue(contractType)
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

    @Test
    fun triggerSmartContract() {
        val result = tronApi.triggerSmartContract(
            "TG3XXyExBkPp9nzdajDZsozEu4BkaSJozs",
            "balanceOf(address)",
            "00000000000000000000004115208EF33A926919ED270E2FA61367B2DA3753DA0000000000000000000000000000000000000000000000000000000000000032",
            visible = true,
            ownerAddress = "TZ4UXDV5ZhNW7fb2AMSbgfAEZ7hWsnYS2g"
        )
//        assertTrue(result.data != null && result.code == 200, result.message)
        println(result)
    }

    @Test
    fun getAccountResource() {
        val result = tronApi.getAccountResource("TLaGjwhvA8XQYSxFAcAXy7Dvuue9eGYitv", visible = true)
        assertTrue(result.data != null && result.code == 200, result.message)
        println(result)
    }

    @Test
    fun broadcastTransaction() {
        val result =
            tronApi.broadcastHex("0a93020a02b8d82208e1cb0249073f613840e0e0c3b4d9325af401081f12ef010a31747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e54726967676572536d617274436f6e747261637412b9010a1541fea35da975a1cd11b0c8883976a35ad0173abf03121541a614f803b6fd780986a42c78ec9c7f77e6ded13c228801613930353963626230303030303030303030303030303030303030303030303034313231303962633936343933623966373338313838633332623865306334393031346135303835303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030317097d1aea3d932124123ce8dc5e3ba4cfbb3e7e8061593dae3e073bdae644e021b56655ebf04b2734e2352dfde328cad175c31c0218aa66ead80b87e7d3ee72eea2eec9d3e19b4f55f00")
        assertTrue(result.data != null && result.code == 200, result.message)
        println(result)
    }
}