package com.github.wenpiner.tron.block

import cn.hutool.core.util.HexUtil
import com.github.wenpiner.tron.block.data.Address
import com.github.wenpiner.tron.block.utils.toBytesPaddedHex
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class AddressTest {
    @Test
    fun fromAddress() {
        val address = Address("41a614f803b6fd780986a42c78ec9c7f77e6ded13c")

        println("Hex: ${address.toHexAddress()}")
        println("Base58: $address")
        assertEquals("TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t", address.toString())
    }

    @Test
    fun testZero() {
        val address = Address("41a614f803b6fd780986a42c78ec9c7f77e6ded13c")
        // 打印地址
        println("Hex:${address.toBytes().toBytesPaddedHex(32)}")
    }
}