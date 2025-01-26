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

package dev.shtanko.algorithms.sorts

import java.util.stream.Stream
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider

class InputIntArrayArgumentsProvider : ArgumentsProvider {
    override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
        Arguments.of(
            intArrayOf(),
            intArrayOf(),
        ),
        Arguments.of(
            intArrayOf(4),
            intArrayOf(4),
        ),
        Arguments.of(
            intArrayOf(-4),
            intArrayOf(-4),
        ),
        Arguments.of(
            intArrayOf(4, 8),
            intArrayOf(4, 8),
        ),
        Arguments.of(
            intArrayOf(-4, -8),
            intArrayOf(-8, -4),
        ),
        Arguments.of(
            intArrayOf(4, 4),
            intArrayOf(4, 4),
        ),
        Arguments.of(
            intArrayOf(42, 23),
            intArrayOf(23, 42),
        ),
        Arguments.of(
            intArrayOf(1, 2, -3),
            intArrayOf(-3, 1, 2),
        ),
        Arguments.of(
            intArrayOf(42, 23, 16, 15, 8, 4),
            intArrayOf(4, 8, 15, 16, 23, 42),
        ),
        Arguments.of(
            intArrayOf(15, 8, 16, 4, 42, 23),
            intArrayOf(4, 8, 15, 16, 23, 42),
        ),
        Arguments.of(
            intArrayOf(4, 8, 15, 16, 23, 42),
            intArrayOf(4, 8, 15, 16, 23, 42),
        ),
        Arguments.of(
            intArrayOf(-4, -8, -15, -16, -23, -42),
            intArrayOf(-42, -23, -16, -15, -8, -4),
        ),
        Arguments.of(
            intArrayOf(-42, -23, -16, -15, -8, -4),
            intArrayOf(-42, -23, -16, -15, -8, -4),
        ),
        Arguments.of(
            intArrayOf(4, 8, 15, 16, 42, 23),
            intArrayOf(4, 8, 15, 16, 23, 42),
        ),
        Arguments.of(
            intArrayOf(45, 23, 53, 43, 18, 24, 8, 95, 101),
            intArrayOf(8, 18, 23, 24, 43, 45, 53, 95, 101),
        ),
        Arguments.of(
            intArrayOf(41, 15, 82, 5, 65, 19, 32, 43, 8),
            intArrayOf(5, 8, 15, 19, 32, 41, 43, 65, 82),
        ),
        Arguments.of(
            intArrayOf(22, 7, 2, -5, 8, 4),
            intArrayOf(-5, 2, 4, 7, 8, 22),
        ),
        Arguments.of(
            intArrayOf(61, 109, 149, 111, 34, 2, 24, 119, 122, 125, 27, 145),
            intArrayOf(2, 24, 27, 34, 61, 109, 111, 119, 122, 125, 145, 149),
        ),
    )
}
