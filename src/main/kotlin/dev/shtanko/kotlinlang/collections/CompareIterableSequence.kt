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

package dev.shtanko.kotlinlang.collections

import dev.shtanko.extensions.isEven

/**
 * Retrieves the first element from a list after applying a series of transformations.
 *
 * @param list The input list of integers.
 * @return A [Triple] containing the counts of map, filter, and forEach operations performed.
 */
fun getFirstFromList(list: IntRange): Triple<Int, Int, Int> {
    var mapCount = 0
    var filterCount = 0
    var forEachCount = 0
    list
        .map { mapCount++; println("map $it"); it * 2 }
        .filter { filterCount++; println("filter $it"); it.isEven }
        .take(1)
        .forEach { forEachCount++; println("list $it") }
    return Triple(mapCount, filterCount, forEachCount)
}

/**
 * Retrieves the first element from a sequence after applying a series of transformations.
 *
 * @param sequence The input sequence of integers.
 * @return A [Triple] containing the counts of map, filter, and forEach operations performed.
 */
fun getFirstFromSequence(sequence: Sequence<Int>): Triple<Int, Int, Int> {
    var mapCount = 0
    var filterCount = 0
    var forEachCount = 0
    sequence
        .map { mapCount++; println("map $it"); it * 2 }
        .filter { filterCount++; println("filter $it"); it.isEven }
        .take(1)
        .forEach { forEachCount++; println("sequence $it") }
    return Triple(mapCount, filterCount, forEachCount)
}
