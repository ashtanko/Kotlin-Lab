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
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class SharedFlowExampleTest {

    @Test
    fun `test shared flow emits events with turbine`() = runTest {
        val sharedFlowExample = SharedFlowExample()
        sharedFlowExample.events.test {
            sharedFlowExample.apply {
                emitEvent("Event1")
                emitEvent("Event2")
            }
            assertThat(awaitItem()).isEqualTo("Event1")
            assertThat(awaitItem()).isEqualTo("Event2")
            cancelAndIgnoreRemainingEvents()
        }
    }
}
