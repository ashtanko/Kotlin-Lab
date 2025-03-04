/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 1545. Find Kth Bit in Nth Binary String
 * @see <a href="https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/">Source</a>
 */
@Medium("https://leetcode.com/problems/find-kth-bit-in-nth-binary-string")
fun interface FindKthBit {
    operator fun invoke(n: Int, k: Int): Char
}

class FindKthBitwise : FindKthBit {
    override fun invoke(n: Int, k: Int): Char {
        val positionInSection = k and -k
        val isInInvertedPart = ((k / positionInSection) shr 1) and 1 == 1
        val originalBitIsOne = (k and 1) == 0
        return if (isInInvertedPart) {
            if (originalBitIsOne) '0' else '1'
        } else {
            if (originalBitIsOne) '1' else '0'
        }
    }
}
