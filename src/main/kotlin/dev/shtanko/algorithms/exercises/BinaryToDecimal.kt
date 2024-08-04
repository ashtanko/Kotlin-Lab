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

package dev.shtanko.algorithms.exercises

import dev.shtanko.algorithms.extensions.isBinary
import java.util.TreeMap

class BinaryToDecimal {

    fun perform(binary: String): Int {
        val powers = getPowers()
        val map: MutableMap<Int, Int> = TreeMap()

        val str = if (binary.isBinary()) binary else ""
        val st = str.reversed()
        val result = st.map { c ->
            Integer.parseInt("$c")
        }
        for (i in result.indices) {
            map[powers[i]] = result[i]
        }

        return map.filter {
            it.value == 1
        }.keys.sum()
    }
}
