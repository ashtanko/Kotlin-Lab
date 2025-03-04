/*
 * Designed and developed by 2025 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.algorithms.arrays

/**
 * Reverse the array in place.
 *
 * @receiver The array to reverse.
 * @return The reversed array.
 *
 * Runtime complexity: O(n)
 * Space complexity: O(1)
 */
fun IntArray.reverse() {
    var l = 0
    var r = this.size - 1
    while (l < r) {
        val tmp = this[l]
        this[l] = this[r]
        this[r] = tmp
        l++
        r--
    }
}
