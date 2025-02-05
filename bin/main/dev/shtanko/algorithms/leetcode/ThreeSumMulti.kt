/*
 * Copyright 2021 Oleksii Shtanko
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

import dev.shtanko.algorithms.MOD

private const val LIMIT = 100
private const val ARR_SIZE = LIMIT + 1

fun interface ThreeSumMulti {
    operator fun invoke(arr: IntArray, target: Int): Int
}

/**
 * Approach 1: Three Pointer
 * Time Complexity: O(N^2), where NN is the length of arr.
 * Space Complexity: O(1).
 */
class TSMThreePointer : ThreeSumMulti {
    override operator fun invoke(arr: IntArray, target: Int): Int {
        var ans: Long = 0
        arr.sort()
        for (i in arr.indices) {
            val t: Int = target - arr[i]
            var j = i + 1
            var k: Int = arr.size - 1
            while (j < k) {
                when {
                    arr[j] + arr[k] < t -> {
                        j++
                    }

                    arr[j] + arr[k] > t -> {
                        k--
                    }

                    arr[j] != arr[k] -> {
                        var left = 1
                        var right = 1
                        while (j + 1 < k && arr[j] == arr[j + 1]) {
                            left++
                            j++
                        }
                        while (k - 1 > j && arr[k] == arr[k - 1]) {
                            right++
                            k--
                        }
                        ans += (left * right).toLong()
                        ans %= MOD.toLong()
                        j++
                        k--
                    }

                    else -> {
                        ans += ((k - j + 1) * (k - j) / 2).toLong()
                        ans %= MOD.toLong()
                        break
                    }
                }
            }
        }

        return ans.toInt()
    }
}

/**
 * Approach 2: Counting with Cases
 * Time Complexity: O(N + W^2).
 * Space Complexity: O(W).
 */
class TSMCountingCases : ThreeSumMulti {
    override operator fun invoke(arr: IntArray, target: Int): Int {
        val count = LongArray(ARR_SIZE)
        for (x in arr) count[x]++

        var ans: Long = 0
        for (x in 0..LIMIT) for (y in x + 1..LIMIT) {
            val z: Int = target - x - y
            if (z in y.plus(1)..LIMIT) {
                ans += count[x] * count[y] * count[z]
                ans %= MOD.toLong()
            }
        }

        for (x in 0..LIMIT) {
            val z: Int = target - 2 * x
            if (z in x.plus(1)..LIMIT) {
                ans += count[x] * (count[x] - 1) / 2 * count[z]
                ans %= MOD.toLong()
            }
        }

        for (x in 0..LIMIT) {
            if (target % 2 == x % 2) {
                val y: Int = (target - x) / 2
                if (y in x.plus(1)..LIMIT) {
                    ans += count[x] * count[y] * count[y].minus(1) / 2
                    ans %= MOD.toLong()
                }
            }
        }

        if (target % 3 == 0) {
            val x: Int = target / 3
            if (x in 0..LIMIT) {
                ans += count[x] * (count[x] - 1) * (count[x] - 2) / 6
                ans %= MOD.toLong()
            }
        }

        return ans.toInt()
    }
}

/**
 * Approach 3: Adapt from Three Sum
 * Time Complexity: O(N^2), where N is the length of A.
 * Space Complexity: O(1).
 */
class TSMAdapt : ThreeSumMulti {
    override operator fun invoke(arr: IntArray, target: Int): Int {
        val count = LongArray(ARR_SIZE)
        var uniq = 0
        for (x in arr) {
            count[x]++
            if (count[x] == 1L) uniq++
        }

        val keys = IntArray(uniq)
        var t = 0
        for (i in 0..LIMIT) if (count[i] > 0) keys[t++] = i

        var ans: Long = 0
        for (i in keys.indices) {
            val x = keys[i]
            var j = i
            var k = keys.size - 1
            val a = target - x
            while (j <= k) {
                val y = keys[j]
                val z = keys[k]
                when {
                    y + z < a -> {
                        j++
                    }

                    y + z > a -> {
                        k--
                    }

                    else -> {
                        ans += when {
                            j in i.plus(1) until k -> {
                                count[x] * count[y] * count[z]
                            }

                            i == j && j < k -> {
                                count[x] * (count[x] - 1) / 2 * count[z]
                            }

                            i < j && j == k -> {
                                count[x] * count[y] * (count[y] - 1) / 2
                            }

                            else -> {
                                count[x] * (count[x] - 1) * (count[x] - 2) / 6
                            }
                        }
                        ans %= MOD.toLong()
                        j++
                        k--
                    }
                }
            }
        }
        return ans.toInt()
    }
}
