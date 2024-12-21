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

abstract class MaxConsecutiveAnswersTest<out T : MaxConsecutiveAnswers>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "TTFF",
                2,
                4,
            ),
            Arguments.of(
                "TFFT",
                1,
                3,
            ),
            Arguments.of(
                "TTFTTFTT",
                1,
                5,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `max consecutive answers test`(answerKey: String, k: Int, expected: Int) {
        val actual = strategy.invoke(answerKey, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class MaxConsecutiveAnswersBSTest : MaxConsecutiveAnswersTest<MaxConsecutiveAnswers>(MaxConsecutiveAnswersBS())
class MaxConsecutiveAnswersSlidingWindowTest :
    MaxConsecutiveAnswersTest<MaxConsecutiveAnswers>(MaxConsecutiveAnswersSlidingWindow())

class MaxConsecutiveAnswersSlidingWindow2Test :
    MaxConsecutiveAnswersTest<MaxConsecutiveAnswers>(MaxConsecutiveAnswersSlidingWindow2())
