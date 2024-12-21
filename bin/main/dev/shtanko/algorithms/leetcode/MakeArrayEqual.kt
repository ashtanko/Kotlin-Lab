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

fun interface MakeArrayEqual {
    operator fun invoke(n: Int): Int
}

/**
 * Approach 1: Brute Force
 * Time complexity: O(N).
 * Space complexity: O(1).
 */
class MakeArrayEqualBruteForce : MakeArrayEqual {
    override operator fun invoke(n: Int): Int {
        var res = 0
        var k = n
        while (k > 0) {
            res += k - 1
            k -= 2
        }
        return res
    }
}

/**
 * Approach 2: Math
 * Time complexity: O(1).
 * Space complexity: O(1).
 */
class MakeArrayEqualMath : MakeArrayEqual {
    override operator fun invoke(n: Int): Int {
        return if (n % 2 == 0) n * n / 4 else (n * n - 1) / 4
    }
}
