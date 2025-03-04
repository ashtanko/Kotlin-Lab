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
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class HotFlowsTest {

    @Test
    fun `hot flow - first - AssertJ`() = runTest {
        val actual = MutableStateFlow("test").first() // first() cancels the Flow
        assertThat(actual).isEqualTo("test")
    }

    @Test
    fun `hot flow - first - Turbine`() = runTest {
        MutableStateFlow("test").test {
            assertThat(awaitItem()).isEqualTo("test")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `hot flow - first - alternative - Turbine`() = runTest {
        MutableStateFlow("test").test {
            assertThat(awaitItem()).isEqualTo("test")
            cancelAndConsumeRemainingEvents()
        }
    }
}
