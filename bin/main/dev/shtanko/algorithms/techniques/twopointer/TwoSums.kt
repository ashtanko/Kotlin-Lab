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

package dev.shtanko.algorithms.techniques.twopointer

object TwoSums {
    fun twoPointers(arr: IntArray, target: Int): Boolean {
        var pointerOne = 0
        var pointerTwo = arr.size - 1

        while (pointerOne < pointerTwo) {
            val sum = arr[pointerOne] + arr[pointerTwo]
            if (sum == target) {
                return true
            } else if (sum < target) {
                pointerOne++
            } else {
                pointerTwo--
            }
        }
        return false
    }
}
