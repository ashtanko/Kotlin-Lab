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

package dev.shtanko.algorithms.bitwise

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BitwiseIntArrayTest {
    @Test
    fun `test findUnique when finding the unique element in an array`() {
        assertEquals(1, intArrayOf(2, 2, 3, 3, 1).findUnique()) // 1 is the unique element
        assertEquals(5, intArrayOf(5, 3, 3, 2, 2).findUnique()) // 5 is the unique element
        assertEquals(7, intArrayOf(4, 7, 4).findUnique()) // 7 is the unique element
        assertEquals(0, intArrayOf(0).findUnique()) // 0 is the unique element
        assertEquals(-1, intArrayOf(-1, 2, 2).findUnique()) // -1 is the unique element
    }
}
