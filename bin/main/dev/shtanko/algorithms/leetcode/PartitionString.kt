/*
 * Copyright 2022 Oleksii Shtanko
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
 * 2405. Optimal Partition of String
 * @see <a href="https://leetcode.com/problems/optimal-partition-of-string/">Source</a>
 */
fun interface PartitionString {
    operator fun invoke(s: String): Int
}

class PartitionStringBit : PartitionString {
    override operator fun invoke(s: String): Int {
        var vis = 0
        var res = 1

        for (c in s) {
            vis = if (vis shr c.code - 'a'.code and 1 == 1) { // if bit is on
                res++ // create new substring
                1 shl c.code - 'a'.code // reset vis and mark current bit on
            } else {
                vis or (1 shl c.code - 'a'.code) // mark the current bit on
            }
        }
        return res
    }
}

class PartitionStringSet : PartitionString {
    override operator fun invoke(s: String): Int {
        var ans = 1
        val st = HashSet<Char>()
        for (i in s.indices) {
            // insert till we find duplicate element.
            if (!st.contains(s[i])) {
                st.add(s[i])
            } else {
                // if we found duplicate char then increment count and clear set and start with new set.
                ans++
                st.clear()
                st.add(s[i])
            }
        }
        return ans
    }
}
