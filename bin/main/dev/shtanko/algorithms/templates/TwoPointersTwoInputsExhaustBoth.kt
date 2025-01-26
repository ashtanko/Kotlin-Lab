/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.algorithms.templates

fun twoInputsExhaustBoth(arr1: IntArray, arr2: IntArray): Int {
    var i = 0
    var j = 0
    val ans = 0
    while (i < arr1.size && j < arr2.size) {
        // do some logic here
        val condition = false
        if (condition) {
            i++
        } else {
            j++
        }
    }
    while (i < arr1.size) {
        // do logic
        i++
    }
    while (j < arr2.size) {
        // do logic
        j++
    }
    return ans
}
