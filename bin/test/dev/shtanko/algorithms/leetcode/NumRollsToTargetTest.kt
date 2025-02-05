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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class NumRollsToTargetTest<out T : NumRollsToTarget>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                1,
                6,
                3,
                1,
            ),
            Arguments.of(
                2,
                6,
                7,
                6,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `number of dice rolls with target sum test`(num: Int, k: Int, target: Int, expected: Int) {
        val actual = strategy(num, k, target)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class NumRollsToTargetBaseTest : NumRollsToTargetTest<NumRollsToTarget>(NumRollsToTargetBase())
class NumRollsToTargetTopDownDpTest : NumRollsToTargetTest<NumRollsToTarget>(NumRollsToTargetTopDownDp())
class NumRollsToTargetBottomUpDpTest : NumRollsToTargetTest<NumRollsToTarget>(NumRollsToTargetBottomUpDp())
