package com.github.wenpiner.tron.block.utils

import cn.hutool.core.codec.Base58
import cn.hutool.crypto.digest.DigestUtil
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.math.BigInteger


/*---- Class constants ----*/
const val ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz" // Everything except 0OIl

val ALPHABET_SIZE: BigInteger = BigInteger.valueOf(ALPHABET.length.toLong())

fun base58ToRawBytes(s: String): ByteArray {
    // Parse base-58 string
    var num = BigInteger.ZERO
    for (element in s) {
        num = num.multiply(ALPHABET_SIZE)
        val digit: Int = ALPHABET.indexOf(element)
        require(digit != -1) { "Invalid character for Base58Check" }
        num = num.add(BigInteger.valueOf(digit.toLong()))
    }

    var b = num.toByteArray()
    if (b[0] == 0.toByte()) {
        b = b.copyOfRange(1, b.size)
    }

    try {

        // Convert leading '1' characters to leading 0-value bytes
        val buf = ByteArrayOutputStream()
        for (element in s) {
            if (element == ALPHABET[0]) {
                buf.write(0)
            } else {
                break
            }
        }
        buf.write(b)
        return buf.toByteArray()
    } catch (e: IOException) {
        throw RuntimeException(e)
    }
}

fun base58ToBytes(base58: String): ByteArray {
    val bytes = base58ToRawBytes(base58)
    val stripSignByte = bytes.size > 1 && bytes[0].toInt() == 0 && bytes[1] < 0

    val data = bytes.copyOfRange(if (stripSignByte) 1 else 0, bytes.size - 4)
    val checksum = bytes.copyOfRange(bytes.size - 4, bytes.size)

    DigestUtil.sha256(data).let {
        DigestUtil.sha256(it).let { i2 ->
            if (!checksum.contentEquals(i2.copyOfRange(0, 4))) {
                throw Exception("Checksum mismatch")
            }
        }
    }
    return data
}


fun base58Encode(bytes: ByteArray): String {

    DigestUtil.sha256(bytes).let {
        DigestUtil.sha256(it).let { i2 ->
            val checksum = i2.copyOfRange(0, 4)
            val input = ByteArray(bytes.size + 4)
            System.arraycopy(bytes, 0, input, 0, bytes.size)
            System.arraycopy(checksum, 0, input, bytes.size, 4)
            return Base58.encode(input)
        }
    }
}

fun countLeadingZeroes(bytes: ByteArray): Int {
    var count = 0
    for (b in bytes) {
        if (b == 0.toByte()) {
            count++
        } else {
            break
        }
    }
    return count
}

fun bytesToBase58(bytes: ByteArray): String {
    return rawBytesToBase58(addCheckHash(bytes))
}

fun rawBytesToBase58(data: ByteArray): String {
    // Convert to base-58 string

    // Convert to base-58 string
    val sb = java.lang.StringBuilder()
    var num = BigInteger(1, data)
    while (num.signum() != 0) {
        val quotrem = num.divideAndRemainder(ALPHABET_SIZE)
        sb.append(ALPHABET.get(quotrem[1].toInt()))
        num = quotrem[0]
    }

    // Add '1' characters for leading 0-value bytes
    for (i in data.indices) {
        if (data[i] == 0.toByte()) {
            sb.append(ALPHABET.get(0))
        } else {
            break
        }
    }
    return sb.reverse().toString()
}

fun addCheckHash(bytes: ByteArray): ByteArray {
    val hash = DigestUtil.sha256(DigestUtil.sha256(bytes))
    val result = ByteArray(bytes.size + 4)
    System.arraycopy(bytes, 0, result, 0, bytes.size)
    System.arraycopy(hash, 0, result, bytes.size, 4)
    return result
}