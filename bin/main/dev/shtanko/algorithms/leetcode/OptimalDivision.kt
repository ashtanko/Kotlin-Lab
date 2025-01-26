/*
 * Copyright 2020 Oleksii Shtanko
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

class Division {
    var maxVal: Float = 0f
    var minVal: Float = 0f
    var minStr: String = ""
    var maxStr: String = ""
}

fun interface OptimalDivisionStrategy {
    operator fun invoke(nums: IntArray): String
}

class OptimalDivisionBruteForce : OptimalDivisionStrategy {

    override operator fun invoke(nums: IntArray): String {
        val t = optimal(nums, 0, nums.size - 1)
        return t.maxStr
    }

    private fun optimal(nums: IntArray, start: Int, end: Int): Division {
        val division = Division()
        if (start == end) {
            division.maxVal = nums[start].toFloat()
            division.minVal = nums[start].toFloat()
            division.minStr = "" + nums[start]
            division.maxStr = "" + nums[start]
            return division
        }
        division.minVal = Float.MAX_VALUE
        division.maxVal = Float.MIN_VALUE
        division.maxStr = ""
        division.minStr = division.maxStr
        for (i in start until end) {
            val left = optimal(nums, start, i)
            val right = optimal(nums, i + 1, end)
            if (division.minVal > left.minVal / right.maxVal) {
                division.minVal = left.minVal / right.maxVal
                division.minStr =
                    left.minStr + "/" + (if (i + 1 != end) "(" else "") + right.minStr + if (i + 1 != end) ")" else ""
            }
            if (division.maxVal < left.maxVal / right.minVal) {
                division.maxVal = left.maxVal / right.minVal
                division.maxStr =
                    left.maxStr + "/" + (if (i + 1 != end) "(" else "") + right.minStr + if (i + 1 != end) ")" else ""
            }
        }
        return division
    }
}

class OptimalDivisionMemorization : OptimalDivisionStrategy {
    override operator fun invoke(nums: IntArray): String {
        val memo: Array<Array<Division>> = Array(nums.size) { Array<Division>(nums.size) { Division() } }
        val division: Division = optimal(nums, 0, nums.size - 1, memo)
        return division.maxStr
    }

    private fun optimal(nums: IntArray, start: Int, end: Int, memo: Array<Array<Division>>): Division {
        val division = Division()
        if (start == end) {
            division.maxVal = nums[start].toFloat()
            division.minVal = nums[start].toFloat()
            division.minStr = "" + nums[start]
            division.maxStr = "" + nums[start]
            memo[start][end] = division
            return division
        }
        division.minVal = Float.MAX_VALUE
        division.maxVal = Float.MIN_VALUE
        division.maxStr = ""
        division.minStr = division.maxStr

        for (i in start until end) {
            val left: Division = optimal(nums, start, i, memo)
            val right: Division = optimal(nums, i + 1, end, memo)
            if (division.minVal > left.minVal / right.maxVal) {
                division.minVal = left.minVal / right.maxVal
                division.minStr =
                    left.minStr + "/" + (if (i + 1 != end) "(" else "") + right.maxStr + if (i + 1 != end) ")" else ""
            }
            if (division.maxVal < left.maxVal / right.minVal) {
                division.maxVal = left.maxVal / right.minVal
                division.maxStr =
                    left.maxStr + "/" + (if (i + 1 != end) "(" else "") + right.minStr + if (i + 1 != end) ")" else ""
            }
        }
        try {
            memo[start][end] = division
        } catch (ignore: ArrayIndexOutOfBoundsException) {
            return division
        }
        return division
    }
}

class MathOptimalDivision : OptimalDivisionStrategy {
    override operator fun invoke(nums: IntArray): String {
        if (nums.isEmpty()) return ""
        if (nums.size == 1) return nums.first().toString() + ""
        if (nums.size == 2) return nums.first().toString() + "/" + nums[1]
        val res = StringBuilder(nums.first().toString() + "/(" + nums[1])
        for (i in 2 until nums.size) {
            res.append("/" + nums[i])
        }
        res.append(")")
        return res.toString()
    }
}
