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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class DinnerPlateStacksTest {

    private class InputCapacityArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(DinnerPlates(2)),
            Arguments.of(DinnerPlatesTree(2)),
        )
    }

    private class InputZeroCapacityArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(DinnerPlates(0)),
            Arguments.of(DinnerPlatesTree(0)),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputCapacityArgumentsProvider::class)
    fun `dinner plate stacks test`(obj: DinnerPlatesDS) {
        obj.push(1)
        obj.push(2)
        obj.push(3)
        obj.push(4)
        obj.push(5)

        assertThat(obj.popAtStack(0)).isEqualTo(2)
        obj.push(20)
        obj.push(21)
        assertThat(obj.popAtStack(0)).isEqualTo(20)
        assertThat(obj.popAtStack(2)).isEqualTo(21)
        assertThat(obj.pop()).isEqualTo(5)
        assertThat(obj.pop()).isEqualTo(4)
        assertThat(obj.pop()).isEqualTo(3)
        assertThat(obj.pop()).isEqualTo(1)
        assertThat(obj.pop()).isEqualTo(-1)
    }

    @ParameterizedTest
    @ArgumentsSource(InputZeroCapacityArgumentsProvider::class)
    fun `dinner plate stacks empty tree test`(obj: DinnerPlatesDS) {
        assertThat(obj.pop()).isEqualTo(-1)
    }

    @ParameterizedTest
    @ArgumentsSource(InputZeroCapacityArgumentsProvider::class)
    fun `dinner plate stacks empty push test`(obj: DinnerPlatesDS) {
        obj.push(1)
        assertThat(obj.pop()).isEqualTo(1)
    }

    @ParameterizedTest
    @ArgumentsSource(InputZeroCapacityArgumentsProvider::class)
    fun `dinner plate stacks popAtStack test`(obj: DinnerPlatesDS) {
        assertThat(obj.popAtStack(1)).isEqualTo(-1)
    }
}
