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

package dev.shtanko.patterns.behavioral.chainofresponsibility.example1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class OrcKingTest {
    /**
     * All possible requests
     */
    private val requests = listOf(
        Request(RequestType.DEFEND_CASTLE, "Don't let the barbarians enter my castle!!"),
        Request(RequestType.TORTURE_PRISONER, "Don't just stand there, tickle him!"),
        Request(RequestType.COLLECT_TAX, "Don't steal, the King hates competition ..."),
    )

    @Test
    fun `test make request`() {
        val king = OrcKing()

        requests.forEach { request: Request ->
            king.makeRequest(request)
            Assertions.assertTrue(
                request.isHandled(),
                "Expected all requests from King to be handled, but [$request] was not!",
            )
        }
    }
}
