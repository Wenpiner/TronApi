# Kotlin Tron HTTP Client Library

This is a Kotlin library for interacting with the Tron blockchain. It provides a set of functions to easily interact
with Tron, including retrieving blocks, transactions, and other related information.

[![](https://jitpack.io/v/Wenpiner/TronApi.svg)](https://jitpack.io/#Wenpiner/TronApi)

## Features

This library offers the following features for interacting with the Tron blockchain:

- **/wallet/getblockbyid**: Get block information by block ID.
- **/wallet/getblockbylatestnum**: Get the latest block information.
- **/wallet/getnowblock**: Get information about the current block.
- **/wallet/getblockbynum**: Get block information by block number.
- **/wallet/getblock**: Get a list of blocks.
- **/wallet/gettransactioninfobyid**: Get transaction information by transaction ID.
- **/wallet/gettransactioninfobyblocknum**: Get transaction information in a block by block number.
- **/wallet/gettransactionbyid**: Get transaction details by transaction ID.
- **/wallet/triggersmartcontract**: Trigger a smart contract.
## Installation

You can reference this library using JitPack. Add the following code to your project's root `build.gradle` file:

```groovy
allprojects {
    repositories {
        // Add the JitPack repository
        maven { url 'https://jitpack.io' }
    }
}
```

Then, add the dependency to your app module's build.gradle file:

```groovy
dependencies {
implementation 'com.github.Wenpiner:TronApi:1.0.0'
}

```

## Usage Examples
Here are some simple examples of using this library:
```kotlin
import com.github.wenpiner.tron.block.api.TronApi

fun main() {
    val tronClient = TronApi() // Replace with the actual Tron API URL

    // Get the latest block
    val latestBlock = tronClient.getBlockByLatestNum()
    println("Latest Block: $latestBlock")

    // Get a block by block number
    val blockByNumber = tronClient.getBlockByNum(12345)
    println("Block #12345: $blockByNumber")

    // Get transaction details
    val transactionInfo = tronClient.getTransactionInfoById("your-transaction-id")
    println("Transaction Info: $transactionInfo")
}

```

## Unofficial Reference Documentation
If you want to learn more about the Tron blockchain API, you can refer to the [Tron Developer Documentation](https://developers.tron.network/reference).

## License
This library is licensed under the MIT License. See the LICENSE file for more information.