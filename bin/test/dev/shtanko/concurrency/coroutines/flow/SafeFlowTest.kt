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

package dev.shtanko.concurrency.coroutines.flow

import dev.shtanko.concurrency.TestBase
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.yield
import org.junit.jupiter.api.Test

class SafeFlowTest : TestBase() {
    @Test
    fun `emissions from different state machine test`() = runTest {
        val result = flow {
            emit1(1)
            emit2(2)
        }.onEach { yield() }.toList()
        assertEquals(listOf(1, 2), result)
        finish(3)
    }

    private suspend fun FlowCollector<Int>.emit1(expect: Int) {
        emit(expect)
        expect(expect)
    }

    private suspend fun FlowCollector<Int>.emit2(expect: Int) {
        emit(expect)
        expect(expect)
    }
}
