/*
 * Copyright 2020 Alexey Shtanko
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

package dev.shtanko.algorithms.leetcode

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class DesignParkingSystemTest {

    @Test
    internal fun `parking system test`() {
        val parkingSystem = DesignParkingSystem(1, 1, 0)
        assertTrue(parkingSystem.addCar(1))
        assertTrue(parkingSystem.addCar(2))
        assertFalse(parkingSystem.addCar(3))
        assertFalse(parkingSystem.addCar(1))
    }
}
