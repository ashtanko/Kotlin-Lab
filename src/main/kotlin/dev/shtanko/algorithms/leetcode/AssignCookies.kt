/*
 * Copyright 2024 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.TwoPointers
import dev.shtanko.algorithms.annotations.level.Easy

/**
 * 455. Assign Cookies
 * @see <a href="https://leetcode.com/problems/assign-cookies">Source</a>
 */
@Easy("https://leetcode.com/problems/assign-cookies")
fun interface AssignCookies {
    operator fun invoke(greedFactor: IntArray, size: IntArray): Int
}

@TwoPointers
class AssignCookiesTwoPointer : AssignCookies {
    override fun invoke(greedFactor: IntArray, size: IntArray): Int {
        greedFactor.sort()
        size.sort()
        var contentChildren = 0
        var cookieIndex = 0
        while (cookieIndex < size.size && contentChildren < greedFactor.size) {
            if (size[cookieIndex] >= greedFactor[contentChildren]) {
                contentChildren++
            }
            cookieIndex++
        }
        return contentChildren
    }
}
