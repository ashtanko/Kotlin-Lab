/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.collections

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.jupiter.api.Test

internal class ConstructingCollectionsTest {

    @Test
    internal fun `constructing map using apply`() {
        val numbersMap = mutableMapOf<String, String>().apply {
            this["one"] = "1"; this["two"] = "2"
        }
        assertThat(numbersMap, equalTo(mapOf("one" to "1", "two" to "2")))
    }

    @Test
    internal fun `initializer functions for lists`() {
        val doubled = List(3) { it * 2 }
        assertThat(doubled, containsInAnyOrder(0, 2, 4))
    }
}
