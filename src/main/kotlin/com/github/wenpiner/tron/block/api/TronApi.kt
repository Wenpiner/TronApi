package com.github.wenpiner.tron.block.api

import com.github.wenpiner.tron.block.api.interceptor.LogInterceptor
import com.github.wenpiner.tron.block.data.*
import com.github.wenpiner.tron.block.data.transaction.TransactionInfo
import com.github.wenpiner.tron.block.utils.toBytesPaddedHex
import com.google.gson.Gson
import okhttp3.Headers
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.io.InputStreamReader
import java.time.Duration

class TronApi
    (baseUrls: Array<String>, headers: Array<Headers?> = emptyArray(), timeout: Duration = Duration.ofSeconds(3)) {
    private var okHttpClient: OkHttpClient
    private val gson = Gson()
    private lateinit var infos: List<Pair<String, Headers>>

    init {
        val builder = OkHttpClient.Builder()
            .connectTimeout(timeout)
            .readTimeout(timeout)
            .writeTimeout(timeout)
        okHttpClient = builder.build()
        infos = baseUrls.mapIndexed { index, baseUrl ->
            val header = if (headers.size > index) headers[index] else null
            Pair(
                baseUrl, header ?: Headers.Builder()
                    .add("Content-Type", "application/json")
                    .build()
            )
        }
    }

    // 获取块
    fun getBlockById(id: String, visible: Boolean = false): Result<Block> {
        return post(
            "/wallet/getblockbyid", mapOf(
                "value" to id,
                "visible" to visible,
            ), Block::class.java
        )
    }

    // 获取块
    fun getBlockByLatestNum(count: Int, visible: Boolean = false): Result<BlockList> {
        return post(
            "/wallet/getblockbylatestnum", mapOf(
                "num" to count,
                "visible" to visible,
            ), BlockList::class.java
        )
    }

    fun getNowBlock(visible: Boolean = false): Result<Block> {
        return post(
            "/wallet/getnowblock", mapOf(
                "visible" to visible,
            ), Block::class.java
        )
    }

    fun getBlockByNum(number: Long, visible: Boolean = false): Result<Block> {
        return post(
            "/wallet/getblockbynum", mapOf(
                "num" to number,
                "visible" to visible,
            ), Block::class.java
        )
    }

    fun getBlock(idOrNumber: String, detail: Boolean = false): Result<Block> {
        return post(
            "/wallet/getblock", mapOf(
                "id_or_num" to idOrNumber,
                "detail" to detail,
            ), Block::class.java
        )
    }

    // /wallet/gettransactioninfobyid
    fun getTransactionInfoById(
        id: String,
        visible: Boolean = false
    ): Result<TransactionInfo> {
        return post(
            "/wallet/gettransactioninfobyid", mapOf(
                "value" to id,
                "visible" to visible,
            ), TransactionInfo::class.java
        )
    }

    // /wallet/gettransactioninfobyblocknum
    fun getTransactionInfoByBlockNum(
        num: Long,
        visible: Boolean = false
    ): Result<TransactionInfo> {
        return post(
            "/wallet/gettransactioninfobyblocknum", mapOf(
                "num" to num,
                "visible" to visible,
            ), TransactionInfo::class.java
        )
    }

    fun getTransactionById(
        id: String,
        visible: Boolean = false
    ): Result<com.github.wenpiner.tron.block.data.transaction.Transaction> {
        return post(
            "/wallet/gettransactionbyid", mapOf(
                "value" to id,
                "visible" to visible,
            ), com.github.wenpiner.tron.block.data.transaction.Transaction::class.java
        )
    }

    // /wallet/triggersmartcontract 调用合约
    fun triggerSmartContract(
        contractAddress: String,
        functionSelector: String = "",
        parameter: String = "",
        data: String = "",
        feeLimit: Long = 100000000,
        callValue: Long = 0,
        ownerAddress: String = "",
        visible: Boolean = false,
        callTokenValue: Long = 0L,
        tokenId: Long = 0L,
        permissionId: Long = 0L
    ): Result<TriggerSmartContract> {


        // 动态参数
        val map = mutableMapOf(
            "contract_address" to contractAddress,
            "function_selector" to functionSelector,
            "parameter" to parameter,
            "fee_limit" to feeLimit,
            "call_value" to callValue,
            "data" to data,
            "visible" to visible,
            "call_token_value" to callTokenValue,
            "token_id" to tokenId,
            "Permission_id" to permissionId,
            "owner_address" to ownerAddress,
        )
        return post(
            "/wallet/triggersmartcontract", map, TriggerSmartContract::class.java
        )
    }

    // /wallet/getaccountresource
    fun getAccountResource(
        address: String,
        visible: Boolean = false
    ): Result<com.github.wenpiner.tron.block.data.AccountResource> {
        return post(
            "/wallet/getaccountresource", mapOf(
                "address" to address,
                "visible" to visible,
            ), com.github.wenpiner.tron.block.data.AccountResource::class.java
        )
    }

    // /wallet/getaccount
    fun getAccount(
        address: String,
        visible: Boolean = false
    ): Result<com.github.wenpiner.tron.block.data.GetAccount> {
        return post(
            "/wallet/getaccount", mapOf(
                "address" to address,
                "visible" to visible,
            ), com.github.wenpiner.tron.block.data.GetAccount::class.java
        )
    }

    // /wallet/broadcasttransaction
    fun broadcastTransaction(
        data: Request,
    ): Result<BroadcastTransaction> {
        return post(
            "/wallet/broadcasttransaction", data, BroadcastTransaction::class.java
        )
    }

    // /wallet/broadcasthex
    fun broadcastHex(
        hex: String,
    ): Result<BroadcastHex> {
        return post(
            "/wallet/broadcasthex", mapOf(
                "transaction" to hex,
            ), BroadcastHex::class.java
        )
    }

    private fun <T> post(url: String, body: Any, clazz: Class<T>): Result<T> {
        val json = gson.toJson(body)
        return post(url, json, clazz)
    }

    private fun <T> post(url: String, params: Map<String, Any>, clazz: Class<T>): Result<T> {
        val json = gson.toJson(params)
        return post(url, json, clazz)
    }

    private fun <T> post(url: String, body: String, clazz: Class<T>): Result<T> {
        val (baseUrl, headers) = infos.random()
        val request = Request.Builder()
            .url(baseUrl + url)
            .headers(headers)
            .post(body.toRequestBody("application/json".toMediaType()))
            .build()
        var response : Response? = null
        try {
            response = okHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                // 解析成T泛型，通过gson
                if (response.body == null) {
                    return Result(null, 500, "response body is null")
                }
                val data = response.body!!.string()
                val fromJson = gson.fromJson(data,clazz)
                return Result(fromJson, 200, "")
            }
            // 非 200 状态码
            return Result(null, response.code, response.body?.string() ?: "")
        } catch (e: IOException) {
            return Result(null, 500, e.message ?: "")
        } catch (e: com.google.gson.JsonSyntaxException) {
            e.printStackTrace()
            return Result(null, 500, e.message ?: "")
        } catch (e: com.google.gson.JsonIOException) {
            e.printStackTrace()
            return Result(null, 500, e.message ?: "")
        }finally {
            response?.close()
        }
    }
}

data class Result<T>(val data: T?, val code: Int, val message: String)