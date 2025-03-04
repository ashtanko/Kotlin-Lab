/*
 * Designed and developed by 2025 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.concurrency.coroutines.flow

import app.cash.turbine.test
import app.cash.turbine.turbineScope
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TurbineTest {

    @Test
    fun `turbine single flow test example`() = runFlowTest(flowOf("one")) {
        assertEquals("one", awaitItem())
        awaitComplete()
    }

    @Test
    fun `turbine multiple flows test example`() = runTest {
        turbineScope {
            val turbine1 = flowOf(1).testIn(backgroundScope)
            val turbine2 = flowOf(2).testIn(backgroundScope)
            assertEquals(1, turbine1.awaitItem())
            assertEquals(2, turbine2.awaitItem())
            turbine1.awaitComplete()
            turbine2.awaitComplete()
        }
    }

    @Test
    fun `turbine library test example`() = runTest {
        flowOf("one", "two").test {
            assertEquals("one", awaitItem())
            assertEquals("two", awaitItem())
            awaitComplete()
        }
    }
}
