/*
 * Copyright 2022 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.kotlinlang.delegation.support

import dev.shtanko.kotlinlang.delegation.AreaInputArgumentsProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource

class ClosedWindowTest {
    @ParameterizedTest
    @ArgumentsSource(AreaInputArgumentsProvider::class)
    fun `rectangle delegation test`(width: Int, height: Int, expected: Int) {
        val rectangle = ClosedRectangle(width, height)
        val window = ClosedWindow(rectangle)
        val actual = window.area()
        assertThat(actual).isEqualTo(expected)
    }
}
