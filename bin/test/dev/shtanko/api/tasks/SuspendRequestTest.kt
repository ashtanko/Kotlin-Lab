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

package dev.shtanko.api.tasks

import dev.shtanko.api.contributors.MockGithubService
import dev.shtanko.api.contributors.expectedResults
import dev.shtanko.api.contributors.testRequestData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions

@OptIn(ExperimentalCoroutinesApi::class)
class SuspendRequestTest {
    @org.junit.jupiter.api.Test
    fun `suspend call test`() = runTest {
        val startTime = currentTime
        val result = loadContributorsSuspend(MockGithubService, testRequestData)
        Assertions.assertEquals(expectedResults.users, result, "Wrong result for 'loadContributorsSuspend'")
        val totalTime = currentTime - startTime
        Assertions.assertEquals(
            expectedResults.timeFromStart,
            totalTime,
            "The calls run consequently, so the total virtual time should be 4000 ms: ",
        )
    }
}
