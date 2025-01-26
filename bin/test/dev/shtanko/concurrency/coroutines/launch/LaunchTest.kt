/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.concurrency.coroutines.launch

import dev.shtanko.concurrency.TestBase
import kotlin.test.assertTrue
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import org.junit.jupiter.api.Test

class LaunchTest : TestBase() {
    @Test
    fun `test launch and yield join`() = runTest {
        expect(1)
        val job = launch(start = CoroutineStart.LAZY) {
            expect(4)
            yield() // does nothing -- main waits
            expect(5)
        }
        expect(2)
        yield()
        expect(3)
        assertTrue(!job.isActive && !job.isCompleted)
        job.join()
        assertTrue(!job.isActive && job.isCompleted)
        finish(6)
    }
}
