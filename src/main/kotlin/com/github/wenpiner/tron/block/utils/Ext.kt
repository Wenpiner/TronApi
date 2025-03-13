package com.github.wenpiner.tron.block.utils

import cn.hutool.core.util.StrUtil
import java.math.BigInteger

fun String.cleanHexPrefix() {
    if (containsHexPrefix(this)) {
        this.substring(2)
    }
}

fun String.toBigIntNoPrefix(): BigInteger {
    this.cleanHexPrefix()
    return BigInteger(this, 16)
}

fun String.toBigInt(): BigInteger {
    return toBigIntNoPrefix()
}

fun String.containsHexPrefix(input: String): Boolean {
    return !StrUtil.isEmpty(input) && input.length > 1 && input[0] == '0' && input[1] == 'x'
}

fun ByteArray.toBigInt(): BigInteger {
    return BigInteger(this)
}

// 转换成16进制，长度len，并在前面进行补0
fun ByteArray.toBytesPaddedHex(len: Int): String {
    // 计算需要补零的数量
    val zeroCount = len - this.size
    // paddedBytes 为补零后的数组
    val paddedBytes = ByteArray(len)
    // 将原字节数组复制到新数组的后面
    System.arraycopy(this, 0, paddedBytes, zeroCount, this.size)
    for (i in 0..<zeroCount) {
        paddedBytes[i] = 0
    }

    // 打印补零后的字节数组对应的十六进制字符串用于验证
    val hex = StringBuilder()
    for (b in paddedBytes) {
        hex.append(String.format("%02x", b))
    }
    return hex.toString()
}