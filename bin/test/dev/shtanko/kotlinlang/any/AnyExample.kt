/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.kotlinlang.any

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class AnyExample {

    private class HashCodesInputParamsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of("A", "A", true),
            Arguments.of("A", "B", false),
            Arguments.of("a", "a", true),
            Arguments.of("a", "B", false),
            Arguments.of(1, 1, true),
            Arguments.of(Any(), Any(), false),
            Arguments.of(Broken(), Broken(), true),
            Arguments.of(BrokenHashCode(), BrokenHashCode(), true),
            Arguments.of(BrokenEquals(), BrokenEquals(), true),
        )
    }

    private class HashCodesNullableInputParamsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of("A", "A", true),
            Arguments.of("A", "B", false),
            Arguments.of("a", "a", true),
            Arguments.of("a", "B", false),
            Arguments.of(1, 1, true),
            Arguments.of(0, 0, true),
            Arguments.of(null, null, true),
            Arguments.of(1, null, false),
            Arguments.of(0, null, true),
            Arguments.of(null, 0, true),
        )
    }

    private class SymmetricInputParamsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of("A", "A", true),
            Arguments.of(Any(), Any(), false),
            Arguments.of(Broken(), Broken(), true),
            Arguments.of(BrokenHashCode(), BrokenHashCode(), true),
            Arguments.of(BrokenEquals(), BrokenEquals(), true),
        )
    }

    private class TransitiveInputParamsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(Triple(1, 1, 1), true),
            Arguments.of(Triple(1, 1, 2), false),
            Arguments.of(Triple(Any(), Any(), Any()), false),
            Arguments.of(Triple(Broken(), Broken(), Broken()), true),
            Arguments.of(Triple(BrokenHashCode(), BrokenHashCode(), BrokenHashCode()), true),
            Arguments.of(Triple(BrokenEquals(), BrokenEquals(), BrokenEquals()), true),
        )
    }

    private class ConsistentInputParamsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(Pair(1, 1), true),
            Arguments.of(Pair(Any(), Any()), false),
            Arguments.of(Pair(Broken(), Broken()), true),
            Arguments.of(Pair(BrokenHashCode(), BrokenHashCode()), true),
            Arguments.of(Pair(BrokenEquals(), BrokenEquals()), true),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(HashCodesInputParamsProvider::class)
    fun `compare hash codes for any test`(a: Any, b: Any, expected: Boolean) {
        val actual = compareHashCodesAny(a, b)
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ArgumentsSource(HashCodesNullableInputParamsProvider::class)
    fun `compare nullable hash codes for any test`(a: Any?, b: Any?, expected: Boolean) {
        val actual = compareHashCodesAnyNullable(a, b)
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ArgumentsSource(SymmetricInputParamsProvider::class)
    fun `symmetric test`(x: Any, y: Any, expected: Boolean) {
        val actual = symmetric(x, y)
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ArgumentsSource(TransitiveInputParamsProvider::class)
    fun `transitive test`(data: Triple<Any, Any, Any>, expected: Boolean) {
        val actual = data.isTransitive()
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ArgumentsSource(ConsistentInputParamsProvider::class)
    fun `consistent test`(data: Pair<Any, Any>, expected: Boolean) {
        val actual = data.isConsistent(100)
        assertThat(actual).isEqualTo(expected)
    }

    private class Broken {
        override fun hashCode() = 0
        override fun equals(other: Any?) = true
    }

    private class BrokenHashCode {
        override fun hashCode(): Int {
            return 0
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }
    }

    private class BrokenEquals {
        override fun equals(other: Any?): Boolean = true

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }
}
