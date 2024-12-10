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

package dev.shtanko.algorithms.sorts

import dev.shtanko.extensions.swap

/**
 * Gnome sort (dubbed stupid sort) is a sorting algorithm originally proposed by an Iranian computer scientist
 *  Hamid Sarbazi-Azad (professor of Computer Science and Engineering at Sharif University of Technology)[1] in 2000.
 *  @link(https://en.wikipedia.org/wiki/Gnome_sort)
 */
class GnomeSort : AbstractSortStrategy {
    override fun <T : Comparable<T>> perform(arr: Array<T>) {
        var i = 1
        var j = 2
        while (i < arr.size) {
            if (arr[i - 1] < arr[i]) {
                i = j++
            } else {
                arr.swap(i - 1, i)
                if (--i == 0) {
                    i = j++
                }
            }
        }
    }
}
