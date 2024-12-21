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

import java.util.TreeMap

class DecimalToBinary {
    fun perform(n: Int): String {
        val powers: MutableMap<Int, Int> = TreeMap()
        val list = MaxPowerOfTwo().decompose(n)
        for (power in getPowers()) {
            if (power > n) break
            powers[power] = if (list.contains(power)) 1 else 0
        }
        return powers.map {
            it.value
        }.reversed().joinToString("")
    }
}
