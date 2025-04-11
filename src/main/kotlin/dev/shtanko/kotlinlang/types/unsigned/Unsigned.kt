@file:OptIn(ExperimentalUnsignedTypes::class)

package dev.shtanko.kotlinlang.types.unsigned

fun unsignedBitwiseAnd(value: UByte = 0b10101010u): UByte {
    val mask: UByte = 0b11110000u
    return value and mask // no unexpected sign problems
}

fun buffers() {
    val bytes: UByteArray = ubyteArrayOf(0xFFu, 0x01u, 0x7Fu)
}

/**
 * Sometimes you need the extra upper range — e.g. ULong gives you up to 2^64 - 1,
 * while Long is capped at 2^63 - 1.
 */
const val BIG_COUNTER: ULong = 18_446_744_073_709_551_615u // ULong.MAX_VALUE
