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

package dev.shtanko.kotlinlang.generics

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BoxTest {

    @Test
    fun `int test`() {
        val b1 = Box<Int>(1)
        val b2 = Box(1)
        assertThat(b1.value).isSameAs(1)
        assertThat(b2.value).isSameAs(1)
    }

    @Test
    fun `string test`() {
        val b = Box("")
        assertThat(b.value).isSameAs("")
    }
}
