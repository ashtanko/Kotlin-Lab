/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.kotlinlang.lambda

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class LambdaTest {

    @Test
    internal fun `simple test`() {
        assertEquals(20, mul(10))
        assertEquals(6, mul(3))
    }

    @Test
    internal fun `given list of number when doing operations using lambda should return proper result`() {
        // given
        val listOfNumbers = listOf(1, 2, 3)

        // when
        val sum = listOfNumbers.reduce { a, b -> a + b }

        // then
        assertEquals(6, sum)
    }
}
