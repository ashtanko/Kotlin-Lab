/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.MOD
import dev.shtanko.algorithms.annotations.level.Hard

/**
 * 1220. Count Vowels Permutation
 * @see <a href="https://leetcode.com/problems/count-vowels-permutation/">Source</a>
 */
@Hard("https://leetcode.com/problems/count-vowels-permutation")
fun interface CountVowelsPermutationStrategy {
    operator fun invoke(n: Int): Int
}

sealed interface CountVowelsPermutation {

    /**
     * Approach 1: Dynamic Programming (Bottom-up)
     */
    class BottomUp : CountVowelsPermutationStrategy {
        override operator fun invoke(n: Int): Int {
            val aVowelPermutationCount = LongArray(n)
            val eVowelPermutationCount = LongArray(n)
            val iVowelPermutationCount = LongArray(n)
            val oVowelPermutationCount = LongArray(n)
            val uVowelPermutationCount = LongArray(n)

            aVowelPermutationCount[0] = 1L
            eVowelPermutationCount[0] = 1L
            iVowelPermutationCount[0] = 1L
            oVowelPermutationCount[0] = 1L
            uVowelPermutationCount[0] = 1L

            for (i in 1 until n) {
                val eiCount = eVowelPermutationCount[i - 1].plus(iVowelPermutationCount[i - 1])
                aVowelPermutationCount[i] = eiCount.plus(uVowelPermutationCount[i - 1]).rem(MOD)
                eVowelPermutationCount[i] = aVowelPermutationCount[i - 1].plus(iVowelPermutationCount[i - 1]).rem(MOD)
                iVowelPermutationCount[i] = (eVowelPermutationCount[i - 1] + oVowelPermutationCount[i - 1]) % MOD
                oVowelPermutationCount[i] = iVowelPermutationCount[i - 1] % MOD
                uVowelPermutationCount[i] = (iVowelPermutationCount[i - 1] + oVowelPermutationCount[i - 1]) % MOD
            }

            val aeCount = aVowelPermutationCount[n - 1].plus(eVowelPermutationCount[n - 1])
            val ioCount = iVowelPermutationCount[n - 1].plus(oVowelPermutationCount[n - 1])
            val result: Long = aeCount.plus(ioCount).plus(uVowelPermutationCount[n - 1]).rem(MOD)

            return result.toInt()
        }

        override fun toString() = "bottom up"
    }

    class OptimizedSpace : CountVowelsPermutationStrategy {
        override operator fun invoke(n: Int): Int {
            var aCount: Long = 1
            var eCount: Long = 1
            var iCount: Long = 1
            var oCount: Long = 1
            var uCount: Long = 1

            for (i in 1 until n) {
                val aCountNew = eCount.plus(iCount).plus(uCount).rem(MOD)
                val eCountNew = aCount.plus(iCount).rem(MOD)
                val iCountNew = eCount.plus(oCount).rem(MOD)
                val oCountNew = iCount.rem(MOD)
                val uCountNew = iCount.plus(oCount).rem(MOD)
                aCount = aCountNew
                eCount = eCountNew
                iCount = iCountNew
                oCount = oCountNew
                uCount = uCountNew
            }
            val aeCount = aCount + eCount
            val ioCount = iCount + oCount

            val result = aeCount.plus(ioCount).plus(uCount).rem(MOD)
            return result.toInt()
        }

        override fun toString(): String = "optimized space"
    }

    /**
     * Approach 3: Dynamic Programming (Top-down, Recursion)
     */
    class TopDown : CountVowelsPermutationStrategy {

        private lateinit var memo: Array<LongArray>

        override operator fun invoke(n: Int): Int {
            // each row stands for the length of string
            // each column indicates the vowels
            // specifically, a: 0, e: 1, i: 2, o: 3, u: 4
            memo = Array(n) { LongArray(5) }
            var result: Long = 0
            for (i in 0..4) {
                result = (result + vowelPermutationCount(n - 1, i)) % MOD
            }
            return result.toInt()
        }

        private fun vowelPermutationCount(i: Int, vowel: Int): Long {
            if (memo[i][vowel] != 0L) return memo[i][vowel]
            if (i == 0) {
                memo[i][vowel] = 1
            } else {
                when (vowel) {
                    0 -> {
                        val local = vowelPermutationCount(i - 1, 1) + vowelPermutationCount(i - 1, 2)
                        memo[i][vowel] = (local + vowelPermutationCount(i - 1, 4)) % MOD
                    }

                    1 -> {
                        val local = vowelPermutationCount(i - 1, 0) + vowelPermutationCount(i - 1, 2)
                        memo[i][vowel] = local % MOD
                    }

                    2 -> {
                        val local = vowelPermutationCount(i - 1, 1) + vowelPermutationCount(i - 1, 3)
                        memo[i][vowel] = local % MOD
                    }

                    3 -> {
                        memo[i][vowel] = vowelPermutationCount(i - 1, 2)
                    }

                    4 -> {
                        val local = vowelPermutationCount(i - 1, 2) + vowelPermutationCount(i - 1, 3)
                        memo[i][vowel] = local % MOD
                    }
                }
            }
            return memo[i][vowel]
        }

        override fun toString(): String = "top down"
    }

    class Matrix : CountVowelsPermutationStrategy {
        override fun invoke(n: Int): Int {
            var countA: Long = 1
            var countE: Long = 1
            var countI: Long = 1
            var countO: Long = 1
            var countU: Long = 1

            for (length in 1 until n) {
                // Calculate the next counts for each vowel based on the previous counts
                val nextCountA = countE
                val nextCountE = (countA + countI) % MOD
                val nextCountI = (countA + countE + countO + countU) % MOD
                val nextCountO = (countI + countU) % MOD
                val nextCountU = countA

                // Update the counts with the newly calculated values for the next length
                countA = nextCountA
                countE = nextCountE
                countI = nextCountI
                countO = nextCountO
                countU = nextCountU
            }

            // Calculate the total count of valid strings for length n
            val totalCount = (countA + countE + countI + countO + countU) % MOD

            return totalCount.toInt()
        }

        override fun toString(): String = "matrix"
    }
}
