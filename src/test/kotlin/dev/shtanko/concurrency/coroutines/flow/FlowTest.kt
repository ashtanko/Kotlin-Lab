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

import app.cash.turbine.TurbineTestContext
import app.cash.turbine.test
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest

/**
 * Run a test on a flow. The provided lambda is a suspend function that will be invoked with a
 * [TurbineTestContext] receiver.
 *
 * @param flow The flow to test.
 * @param validate The test to run.
 *
 * @see TurbineTestContext
 * @see runTest
 * @see Flow
 * @see test
 */
fun <T> runFlowTest(flow: Flow<T>, validate: suspend TurbineTestContext<T>.() -> Unit) = runTest {
    flow.test(validate = validate)
}
