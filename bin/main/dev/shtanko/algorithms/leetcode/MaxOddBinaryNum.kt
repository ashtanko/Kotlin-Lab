/*
 * Copyright 2024 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

/**
 * 2864. Maximum Odd Binary Number
 * @see <a href="https://leetcode.com/problems/maximum-odd-binary-number">Source</a>
 */
fun interface MaxOddBinaryNum {
    operator fun invoke(str: String): String
}

val maxOddBinaryNumSort = MaxOddBinaryNum { str ->
    val arr: CharArray = str.toCharArray()
    val size = arr.size

    arr.sort()

    // Reverse order for the first N - 1 elements of the array
    // Because we want to keep a 1 at the last index
    // The last element of the array is index N - 1, and the second to last element is at N - 2
    val secondLast = size - 2
    for (i in 0 until size / 2) {
        val temp = arr[i]
        arr[i] = arr[secondLast - i]
        arr[secondLast - i] = temp
    }

    return@MaxOddBinaryNum String(arr)
}

val maxOddBinaryNumCounting = MaxOddBinaryNum { str ->
    val size: Int = str.length
    // Find ones_cnt
    var onesCnt = 0
    for (i in 0 until size) {
        onesCnt += str[i] - '0'
    }

    // Use StringBuilder for fast concatenation
    val sb = StringBuilder()
    for (i in 0 until onesCnt - 1) {
        sb.append("1")
    }
    for (i in 0 until size - onesCnt) {
        sb.append("0")
    }
    sb.append("1")

    return@MaxOddBinaryNum sb.toString()
}

val maxOddBinaryNumTwoPointers = MaxOddBinaryNum { str ->
    val arr: CharArray = str.toCharArray()
    val size = arr.size

    // Initialize two pointers
    var left = 0
    var right = size - 1

    while (left <= right) {
        // Increment left if equals 1

        if (arr[left] == '1') {
            left++
        }
        // Decrement right if equals 0
        if (arr[right] == '0') {
            right--
        }
        // Swap if neither pointer can be iterated
        if (left <= right && arr[left] == '0' && arr[right] == '1') {
            arr[left] = '1'
            arr[right] = '0'
        }
    }

    // Swap rightmost 1 bit to the end
    arr[left - 1] = '0'
    arr[size - 1] = '1'
    return@MaxOddBinaryNum String(arr)
}
