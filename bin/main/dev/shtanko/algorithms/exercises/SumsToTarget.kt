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

package dev.shtanko.algorithms.exercises

fun interface SumsToTarget {
    fun perform(arr: IntArray, k: Int): Boolean
}

class SumsToTargetBF : SumsToTarget {
    override fun perform(arr: IntArray, k: Int): Boolean {
        for (i in arr.indices) {
            for (j in i + 1 until arr.size) {
                if (arr[i] + arr[j] == k) {
                    return true
                }
            }
        }
        return false
    }
}

class SumsToTargetHashSet : SumsToTarget {
    override fun perform(arr: IntArray, k: Int): Boolean {
        val values: MutableSet<Int> = HashSet()
        for (i in arr.indices) {
            if (values.contains(k - arr[i])) {
                return true
            }
            values.add(arr[i])
        }
        return false
    }
}

class SumsToTargetSort : SumsToTarget {
    override fun perform(arr: IntArray, k: Int): Boolean {
        arr.sort()
        for (i in arr.indices) {
            val siblingIndex = arr.binarySearch(k - arr[i])
            if (siblingIndex >= 0) {
                val left = siblingIndex != i || i > 0 && i - 1 == arr[i]
                val right = i < arr.size - 1 && arr[i + 1] == arr[i]
                if (left || right) {
                    return true
                }
            }
        }
        return false
    }
}
