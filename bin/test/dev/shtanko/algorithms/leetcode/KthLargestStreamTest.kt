/*
 * Copyright 2023 Oleksii Shtanko
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
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class KthLargestStreamTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(KthLargestStreamHeap(args1.first, args1.second)),
        )

        private val args1 = 3 to intArrayOf(4, 5, 8, 2)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `add test`(strategy: KthLargestStream) {
        assertThat(strategy.add(3)).isEqualTo(4)
        assertThat(strategy.add(5)).isEqualTo(5)
        assertThat(strategy.add(10)).isEqualTo(5)
        assertThat(strategy.add(9)).isEqualTo(8)
        assertThat(strategy.add(4)).isEqualTo(8)
    }
}