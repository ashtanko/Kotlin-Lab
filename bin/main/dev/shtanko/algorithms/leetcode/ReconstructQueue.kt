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

import dev.shtanko.extensions.second
import java.util.LinkedList

fun reconstructQueue(people: Array<IntArray>): Array<IntArray> {
    people.sortWith { a, b ->
        if (a.first() == b.first()) a.second() - b.second() else b.first() - a.first()
    }
    val list: MutableList<IntArray> = LinkedList()
    for (p in people) {
        list.add(p.second(), p)
    }
    return list.toTypedArray()
}
