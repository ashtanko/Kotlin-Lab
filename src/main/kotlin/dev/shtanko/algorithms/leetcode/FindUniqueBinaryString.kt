/*
 * Copyright 2022 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import java.util.stream.Collectors
import java.util.stream.IntStream

/**
 * 1980. Find Unique Binary String
 * @see <a href="https://leetcode.com/problems/find-unique-binary-string/">leetcode page</a>
 */
interface FindUniqueBinaryString {
    fun perform(nums: Array<String>): String
}

class FindUniqueBinaryStringImpl : FindUniqueBinaryString {
    override fun perform(nums: Array<String>): String {
        val ans = StringBuilder()
        for (i in nums.indices) {
            ans.append(if (nums[i][i] == '0') '1' else '0')
        }
        return ans.toString()
    }
}

class FindUniqueBinaryStringStream : FindUniqueBinaryString {
    override fun perform(nums: Array<String>): String {
        return IntStream.range(0, nums.size).mapToObj { i -> if (nums[i][i] == '0') "1" else "0" }
            .collect(Collectors.joining())
    }
}

class FindUniqueBinaryStringOneLine : FindUniqueBinaryString {
    override fun perform(nums: Array<String>): String {
        return nums.indices.joinToString("") { i -> if (nums[i][i] == '0') "1" else "0" }
    }
}
