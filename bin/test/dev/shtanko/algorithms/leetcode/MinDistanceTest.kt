/*
 * Copyright 2021 Oleksii Shtanko
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

abstract class MinDistanceTest<out T : MinDistance>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "sea",
                "eat",
                2,
            ),
            Arguments.of(
                "leetcode",
                "etco",
                4,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `min distance test`(word1: String, word2: String, expected: Int) {
        val actual = strategy.invoke(word1, word2)
        assertThat(actual).isEqualTo(expected)
    }
}

class MinDistanceLCSTest : MinDistanceTest<MinDistanceLCS>(MinDistanceLCS())
class MinDistanceLCSMemoTest : MinDistanceTest<MinDistanceLCSMemo>(MinDistanceLCSMemo())
class MinDistanceLCSDPTest : MinDistanceTest<MinDistanceLCSDP>(MinDistanceLCSDP())
class MinDistanceDPTest : MinDistanceTest<MinDistanceDP>(MinDistanceDP())
class MinDistance1DDPTest : MinDistanceTest<MinDistance1DDP>(MinDistance1DDP())
