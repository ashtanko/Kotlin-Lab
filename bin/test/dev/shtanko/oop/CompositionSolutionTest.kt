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

package dev.shtanko.oop

import java.util.TreeSet
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CompositionSolutionTest {
    @Test
    internal fun `composition instrumented hash set test`() {
        val s = CompositionInstrumentedHashSet<String>(TreeSet())
        s.addAll(listOf("Snap", "Crackle", "Pop"))
        val actual = s.getAddCount()
        assertEquals(3, actual) // right value is 3, composition working correctly
    }
}
