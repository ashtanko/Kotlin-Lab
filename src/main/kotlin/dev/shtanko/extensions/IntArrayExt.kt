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

package dev.shtanko.extensions

/**
 * Returns a list containing elements at specified [startIndex] and [endIndex] from the original array.
 *
 * @param startIndex The start index of the range to copy.
 * @param endIndex The end index of the range to copy.
 * @return A list containing elements at specified [startIndex] and [endIndex] from the original array.
 */
fun IntArray.subList(startIndex: Int, endIndex: Int): List<Int> {
    return if (isNotEmpty() && startIndex < endIndex) {
        this.slice(startIndex until endIndex)
    } else {
        emptyList()
    }
}
