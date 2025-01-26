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

package dev.shtanko.algorithms.math

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class KeithNumberTest<out T : KeithNumber>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                1,
                true,
            ),
            Arguments.of(
                2,
                true,
            ),
            Arguments.of(
                3,
                true,
            ),
            Arguments.of(
                4,
                true,
            ),
            Arguments.of(
                5,
                true,
            ),
            Arguments.of(
                6,
                true,
            ),
            Arguments.of(
                7,
                true,
            ),
            Arguments.of(
                8,
                true,
            ),
            Arguments.of(
                9,
                true,
            ),
            Arguments.of(
                10,
                false,
            ),
            Arguments.of(
                14,
                true,
            ),
            Arguments.of(
                19,
                true,
            ),
            Arguments.of(
                28,
                true,
            ),
            Arguments.of(
                47,
                true,
            ),
            Arguments.of(
                61,
                true,
            ),
            Arguments.of(
                62,
                false,
            ),
            Arguments.of(
                75,
                true,
            ),
            Arguments.of(
                76,
                false,
            ),
            Arguments.of(
                197,
                true,
            ),
            Arguments.of(
                742,
                true,
            ),
            Arguments.of(
                1104,
                true,
            ),
            Arguments.of(
                1537,
                true,
            ),
            Arguments.of(
                2208,
                true,
            ),
            Arguments.of(
                2580,
                true,
            ),
            Arguments.of(
                3684,
                true,
            ),
            Arguments.of(
                4788,
                true,
            ),
            Arguments.of(
                7385,
                true,
            ),
            Arguments.of(
                7647,
                true,
            ),
            Arguments.of(
                7909,
                true,
            ),
            Arguments.of(
                31331,
                true,
            ),
            Arguments.of(
                34285,
                true,
            ),
            Arguments.of(
                34348,
                true,
            ),
            Arguments.of(
                55604,
                true,
            ),
            Arguments.of(
                62662,
                true,
            ),
            Arguments.of(
                86935,
                true,
            ),
            Arguments.of(
                93993,
                true,
            ),
            Arguments.of(
                120284,
                true,
            ),
            Arguments.of(
                129106,
                true,
            ),
            Arguments.of(
                147640,
                true,
            ),
            Arguments.of(
                156146,
                true,
            ),
            Arguments.of(
                174680,
                true,
            ),
            Arguments.of(
                183186,
                true,
            ),
            Arguments.of(
                298320,
                true,
            ),
            Arguments.of(
                355419,
                true,
            ),
            Arguments.of(
                694280,
                true,
            ),
            Arguments.of(
                925993,
                true,
            ),
            Arguments.of(
                1084051,
                true,
            ),
            Arguments.of(
                7913837,
                true,
            ),
            Arguments.of(
                11436171,
                true,
            ),
            Arguments.of(
                33445755,
                true,
            ),
            Arguments.of(
                44121607,
                true,
            ),
            Arguments.of(
                129572008,
                true,
            ),
            Arguments.of(
                251133297,
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `is keith test`(num: Int, expected: Boolean) {
        val actual = strategy.isKeith(num)
        assertThat(actual).isEqualTo(expected)
    }
}

class KeithNumberImplTest : KeithNumberTest<KeithNumber>(KeithNumberImpl())
