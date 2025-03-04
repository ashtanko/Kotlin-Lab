/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

fun interface RunningSum {
    operator fun invoke(nums: IntArray): IntArray
}

class RunningSumNaive : RunningSum {
    override operator fun invoke(nums: IntArray): IntArray {
        return nums.naive()
    }

    private fun IntArray.naive(): IntArray {
        if (isEmpty() || size <= 1) return intArrayOf()
        for (i in 1 until size) {
            this[i] += this[i - 1]
        }
        return this
    }
}
