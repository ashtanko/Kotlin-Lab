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

fun interface CanBeEqualStrategy {
    operator fun invoke(target: IntArray, arr: IntArray): Boolean
}

class CanBeEqualSort : CanBeEqualStrategy {
    override operator fun invoke(target: IntArray, arr: IntArray): Boolean {
        target.sort()
        arr.sort()
        return target.contentEquals(arr)
    }
}

class CanBeEqualMap : CanBeEqualStrategy {
    override operator fun invoke(target: IntArray, arr: IntArray): Boolean {
        val n = arr.size
        val map = HashMap<Int, Int?>()
        for (i in 0 until n) {
            if (!map.containsKey(arr[i])) map[arr[i]] = 1 else map[arr[i]] = map[arr[i]]!! + 1
        }
        for (i in 0 until n) {
            if (!map.containsKey(target[i])) return false
            var count = map[target[i]]!!
            count--
            if (count == 0) map.remove(target[i]) else map[target[i]] = count
        }
        return true
    }
}
