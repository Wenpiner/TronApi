package com.github.wenpiner.tron.block.data

import cn.hutool.core.util.HexUtil
import com.github.wenpiner.tron.block.utils.base58ToBytes
import com.github.wenpiner.tron.block.utils.bytesToBase58
import com.github.wenpiner.tron.block.utils.toBigInt
import java.math.BigInteger
import java.util.*


class Address(addr: String) {
    private lateinit var value: ByteArray
    private var bigInteger: BigInteger = if (addr.startsWith("41")) {
        addr.substring(2).toBigInt()
    } else if (addr.startsWith("T")) {
        val rawByte = base58ToBytes(addr)
        Arrays.copyOfRange(rawByte, 1, 21).toBigInt()
    } else {
        addr.toBigInt()
    }

    init {
//        value = if (addr.startsWith("T")) {
//            base58ToBytes(addr)
//        } else if (addr.startsWith("41")) {
//            HexUtil.decodeHex(addr)
//        } else {
//            countLeadingZeroes(HexUtil.decodeHex(addr))
//        }
    }

    fun toHexAddress(): String {
        val rawAddr: ByteArray = toBytesPadded(bigInteger, 21)
        rawAddr[0] = 65
        return HexUtil.encodeHexStr(rawAddr)
    }

    override fun toString(): String {
        val rawAddr: ByteArray = toBytesPadded(bigInteger, 21)
        rawAddr[0] = 65
        return bytesToBase58(rawAddr)
    }

    fun toBytes(): ByteArray {
        return bigInteger.toByteArray()
    }
}



fun toBytesPadded(value: BigInteger, length: Int): ByteArray {
    val result = ByteArray(length)
    val bytes = value.toByteArray()
    val bytesLength: Int
    val srcOffset: Int
    if (bytes[0].toInt() == 0) {
        bytesLength = bytes.size - 1
        srcOffset = 1
    } else {
        bytesLength = bytes.size
        srcOffset = 0
    }
    if (bytesLength > length) {
        throw RuntimeException("Input is too large to put in byte array of size $length")
    }
    val destOffset = length - bytesLength
    System.arraycopy(bytes, srcOffset, result, destOffset, bytesLength)
    return result
}