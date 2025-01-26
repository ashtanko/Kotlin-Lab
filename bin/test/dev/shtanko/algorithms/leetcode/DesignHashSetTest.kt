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

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class DesignHashSetTest {

    private class InputStrategiesProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(DesignHashSetSimple()),
            Arguments.of(DesignHashSetImpl()),
            Arguments.of(DesignHashSetBitSet()),
        )
    }

    @ParameterizedTest()
    @ArgumentsSource(InputStrategiesProvider::class)
    fun `hash set test`(strategy: DesignHashSet) {
        val hashSet: DesignHashSet = strategy
        hashSet.add(1)
        hashSet.add(2)
        assertTrue(hashSet.contains(1))
        assertThat(hashSet.contains(3)).isFalse
        hashSet.add(2)
        assertTrue(hashSet.contains(2))
        hashSet.remove(2)
        assertThat(hashSet.contains(2)).isFalse
    }

    @ParameterizedTest
    @ArgumentsSource(InputStrategiesProvider::class)
    fun `hash set rehash test`(strategy: DesignHashSet) {
        val hashSet: DesignHashSet = strategy
        val num = 32 * 3
        for (i in 0 until num) {
            hashSet.add(i)
        }
        assertTrue(hashSet.contains(num - 1))
    }
}
