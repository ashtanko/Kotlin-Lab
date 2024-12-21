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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

abstract class DesignUndergroundSystemTest<out T : UndergroundSystem>(private val system: T) {

    @Test
    fun `underground system test`() {
        system.checkIn(45, "Leyton", 3)
        system.checkIn(32, "Paradise", 8)
        system.checkIn(27, "Leyton", 10)
        system.checkOut(45, "Waterloo", 15)
        system.checkOut(27, "Waterloo", 20)
        system.checkOut(32, "Cambridge", 22)
        assertEquals(14.000, system.getAverageTime("Paradise", "Cambridge"))
        assertEquals(11.000, system.getAverageTime("Leyton", "Waterloo"))
        system.checkIn(10, "Leyton", 24)
        assertEquals(11.000, system.getAverageTime("Leyton", "Waterloo"))
        system.checkOut(10, "Waterloo", 38)
        assertEquals(12.000, system.getAverageTime("Leyton", "Waterloo"))
    }
}

class UndergroundSystemImplTest : DesignUndergroundSystemTest<UndergroundSystemImpl>(UndergroundSystemImpl())
