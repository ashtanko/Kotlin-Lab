/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.algorithms.templates

/**
 * Binary search: duplicate elements, right-most insertion point
 */
private fun fn(arr: IntArray, target: Int): Int {
    var left = 0
    var right = arr.size
    while (left < right) {
        val mid = left + (right - left) / 2
        if (arr[mid] > target) {
            right = mid
        } else {
            left = mid + 1
        }
    }
    return left
}
