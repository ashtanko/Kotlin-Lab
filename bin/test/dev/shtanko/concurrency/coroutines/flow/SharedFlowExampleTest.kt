/*
 * Copyright 2025 Oleksii Shtanko
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

package dev.shtanko.concurrency.coroutines.flow

import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SharedFlowExampleTest {
    @Test
    fun `test shared flow emits events`() = runTest {
        val sharedFlowExample = SharedFlowExample()
        val emissions = mutableListOf<String>()

        val job = launch {
            sharedFlowExample.events.take(2).toList(emissions)
        }

        sharedFlowExample.emitEvent("Event1")
        sharedFlowExample.emitEvent("Event2")
        job.join()

        assertEquals(listOf("Event1", "Event2"), emissions)
    }
}
