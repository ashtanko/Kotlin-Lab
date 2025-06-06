/*
 * Designed and developed by 2025 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.junit

import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit
import java.util.*
import java.util.function.Supplier
import java.util.stream.IntStream
import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE
import org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ALL
import org.junit.jupiter.params.provider.FieldSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.NullSource
import org.junit.jupiter.params.provider.ValueSource

class ParameterizedTestsTest {

    // region @ValueSource
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3])
    fun testWithValueSource(argument: Int) {
        assertTrue(argument > 0 && argument < 4)
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = [" ", "   ", "\t", "\n"])
    fun nullEmptyAndBlankStrings(text: String?) {
        assertTrue(text == null || text.trim().isEmpty())
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = [" ", "   ", "\t", "\n"])
    fun nullEmptyAndBlankStringsOneAnnotation(text: String?) {
        assertTrue(text == null || text.trim().isEmpty())
    }
    // endregion

    // region @EnumSource
    @ParameterizedTest
    @EnumSource(ChronoUnit::class)
    fun testWithEnumSource(unit: TemporalUnit?) {
        assertNotNull(unit)
    }

    @ParameterizedTest
    @EnumSource
    fun testWithEnumSourceWithAutoDetection(unit: ChronoUnit?) {
        assertNotNull(unit)
    }

    @ParameterizedTest
    @EnumSource(names = ["DAYS", "HOURS"])
    fun testWithEnumSourceInclude(unit: ChronoUnit?) {
        assertTrue(EnumSet.of(ChronoUnit.DAYS, ChronoUnit.HOURS).contains(unit))
    }

    @ParameterizedTest
    @EnumSource(mode = EXCLUDE, names = ["ERAS", "FOREVER"])
    fun testWithEnumSourceExclude(unit: ChronoUnit?) {
        assertFalse(EnumSet.of(ChronoUnit.ERAS, ChronoUnit.FOREVER).contains(unit))
    }

    @ParameterizedTest
    @EnumSource(mode = MATCH_ALL, names = ["^.*DAYS$"])
    fun testWithEnumSourceRegex(unit: ChronoUnit) {
        assertTrue(unit.name.endsWith("DAYS"))
    }
    // endregion

    // region @MethodSource
    @ParameterizedTest
    @MethodSource("stringProvider")
    fun testWithExplicitLocalMethodSource(argument: String) {
        assertNotNull(argument)
    }

    @ParameterizedTest
    @MethodSource
    fun testWithDefaultLocalMethodSource(argument: String) {
        assertNotNull(argument)
    }

    @ParameterizedTest
    @MethodSource("range")
    fun testWithRangeMethodSource(argument: Int) {
        assertNotEquals(9, argument)
    }

    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    fun testWithMultiArgMethodSource(str: String, num: Int, list: List<String>) {
        assertEquals(5, str.length)
        assertTrue(num >= 1 && num <= 2)
        assertEquals(2, list.size)
    }
    // endregion

    // region @FieldSource
    @ParameterizedTest
    @FieldSource
    fun arrayOfFruits(fruit: String) { // uses the field name as the source
        assertTrue(arrayOfFruits.contains(fruit))
    }

    @ParameterizedTest
    @FieldSource("listOfFruits")
    fun singleFieldSource(fruit: String) {
        assertTrue(listOfFruits.contains(fruit))
    }

    @ParameterizedTest
    @FieldSource
    fun namedArgumentsSupplier(fruit: String) {
        println(fruit)
    }
    // endregion

    companion object {

        @JvmStatic
        val arrayOfFruits = arrayOf("apple", "banana")

        @JvmStatic
        val listOfFruits = listOf("apple", "banana")

        @JvmStatic
        fun stringProvider(): List<String> {
            return listOf("apple", "banana")
        }

        @JvmStatic
        fun testWithDefaultLocalMethodSource(): List<String> {
            return listOf("apple", "banana")
        }

        @JvmStatic
        fun range(): IntArray {
            return IntStream.range(0, 20).skip(10).toArray()
        }

        @JvmStatic
        fun stringIntAndListProvider(): List<Array<Any>> {
            return listOf(
                arrayOf("apple", 1, listOf("a", "b")),
                arrayOf("lemon", 2, listOf("x", "y")),
            )
        }

        @JvmStatic
        val namedArgumentsSupplier: Supplier<Stream<Arguments>> = Supplier {
            Stream.of(
                arguments("apple"),
                arguments("banana"),
            )
        }
    }
}
