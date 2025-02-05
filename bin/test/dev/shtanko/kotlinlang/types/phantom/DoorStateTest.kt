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

package dev.shtanko.kotlinlang.types.phantom

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DoorStateTest {

    @Test
    fun `door violation allow to close already closed door test`() {
        assertThat(DoorViolation(Closed).close().state).isInstanceOf(Closed::class.java)
    }

    @Test
    fun `door violation allow to open already opened door test`() {
        assertThat(DoorViolation(Open).open().state).isInstanceOf(Open::class.java)
    }

    @Test
    fun `door solution allows close only opened door`() {
        assertThat(Door(Closed).open().state).isInstanceOf(Open::class.java)
    }

    @Test
    fun `door solution allows open only closed door`() {
        assertThat(Door(Open).close().state).isInstanceOf(Closed::class.java)
    }
}
