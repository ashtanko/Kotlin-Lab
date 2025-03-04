/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.extensions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DoubleExtTest {

    @DisplayName("Test only even numbers")
    @ParameterizedTest(name = "For example, value {0} is even.")
    @ValueSource(doubles = [2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0, 100.0])
    fun `is even test`(value: Double) {
        assertThat(value.isEven).isTrue
    }

    @DisplayName("Test only odd numbers")
    @ParameterizedTest(name = "For example, value {0} is not even.")
    @ValueSource(doubles = [1.0, 11.0, 21.0, 31.0, 41.0, 51.0, 61.0, 71.0, 81.0, 91.0, 3.0, 13.0])
    fun `is odd test`(value: Double) {
        assertThat(value.isEven).isFalse
    }
}
