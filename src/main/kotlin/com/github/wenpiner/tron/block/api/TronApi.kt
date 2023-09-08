package com.github.wenpiner.tron.block.api

import com.github.phish.tron.block.data.Block
import com.github.phish.tron.block.data.BlockList
import com.github.phish.tron.block.data.transaction.TransactionInfo
import com.google.gson.Gson
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.io.InputStreamReader
import java.time.Duration

class TronApi {
    private lateinit var okHttpClient: OkHttpClient
    private val gson = Gson()
    private lateinit var infos: Array<Pair<String, Headers>>

    constructor(
        baseUrls: Array<String>,
        headers: Array<Headers?> = emptyArray(),
        timeout: Duration = Duration.ofSeconds(3)
    ) {
        this.okHttpClient = OkHttpClient.Builder()
            .connectTimeout(timeout)
            .readTimeout(timeout)
            .writeTimeout(timeout)
            .build()
        baseUrls.forEachIndexed { index, baseUrl ->
            val header = if (headers.size > index) headers[index] else null
            infos[index] = Pair(
                baseUrl, header ?: Headers.Builder()
                    .add("Content-Type", "application/json")
                    .build()
            )
        }
    }

    constructor() {
        this.okHttpClient = OkHttpClient.Builder()
            .build()
        infos = arrayOf(
            Pair(
                "https://api.trongrid.io",
                Headers.Builder()
                    .add("Content-Type", "application/json")
                    .build()
            ),
            Pair(
                "https://api.trongrid.io",
                Headers.Builder()
                    .add("Content-Type", "application/json")
                    .build()
            ),
            Pair(
                "https://api.trongrid.io",
                Headers.Builder()
                    .add("Content-Type", "application/json")
                    .build()
            ),
        )
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
    ): Result<com.github.phish.tron.block.data.transaction.Transaction> {
        return post(
            "/wallet/gettransactionbyid", mapOf(
                "value" to id,
                "visible" to visible,
            ), com.github.phish.tron.block.data.transaction.Transaction::class.java
        )
    }

    private fun <T> post(url: String, body: Map<String, Any>, clazz: Class<T>): Result<T> {
        val (baseUrl, headers) = infos.random()
        val request = Request.Builder()
            .url(baseUrl + url)
            .headers(headers)
            .post(body.toRequestBody())
            .build()
        try {
            val response = okHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                // 解析成T泛型，通过gson
                if (response.body == null) {
                    return Result(null, 500, "response body is null")
                }
                val input = InputStreamReader(response.body!!.byteStream())
                val fromJson = gson.fromJson(input, clazz)
                return Result(fromJson, 200, "")
            }
            // 非 200 状态码
            return Result(null, response.code, response.body?.string() ?: "")
        } catch (e: IOException) {
            return Result(null, 500, e.message ?: "")
        } catch (e: com.google.gson.JsonSyntaxException) {
            return Result(null, 500, e.message ?: "")
        } catch (e: com.google.gson.JsonIOException) {
            return Result(null, 500, e.message ?: "")
        }
    }
}

data class Result<T>(val data: T?, val code: Int, val message: String)