package com.github.wenpiner.tron.block.data.transaction

enum class ContractRet {
    DEFAULT,
    SUCCESS,
    REVERT,
    BAD_JUMP_DESTINATION,
    OUT_OF_MEMORY,
    PRECOMPILED_CONTRACT,
    STACK_TOO_SMALL,
    STACK_TOO_LARGE,
    ILLEGAL_OPERATION,
    STACK_OVERFLOW,
    OUT_OF_ENERGY,
    OUT_OF_TIME,
    JVM_STACK_OVER_FLOW,
    UNKNOWN,
    TRANSFER_FAILED,
    UNRECOGNIZED
}
