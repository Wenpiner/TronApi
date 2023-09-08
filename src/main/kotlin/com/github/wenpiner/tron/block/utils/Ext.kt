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