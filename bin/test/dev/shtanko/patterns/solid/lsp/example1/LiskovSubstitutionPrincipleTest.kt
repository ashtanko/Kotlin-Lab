/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.patterns.solid.lsp.example1

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class LiskovSubstitutionPrincipleTest {
    @Test
    fun testBirdFly() {
        val bird: Bird = Bird()
        assertEquals("Bird is flying", bird.fly())
    }

    @Test
    fun testSparrowFly() {
        val sparrow: Bird = Sparrow()
        assertEquals("Sparrow is flying", sparrow.fly())
    }

    @Test
    fun testOstrichFly() {
        val ostrich: Bird = Ostrich()
        assertEquals("Ostrich cannot fly", ostrich.fly())
    }
}
