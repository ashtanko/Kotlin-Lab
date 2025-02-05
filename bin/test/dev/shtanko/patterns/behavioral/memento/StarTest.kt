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

package dev.shtanko.patterns.behavioral.memento

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StarTest {

    @Test
    fun `time passes test`() {
        val star = Star(StarType.SUN, 1, 2)
        assertThat(star.toString()).isEqualTo("sun age: 1 years mass: 2 tons")
        star.timePasses()
        assertThat(star.toString()).isEqualTo("red giant age: 2 years mass: 16 tons")
        star.timePasses()
        assertThat(star.toString()).isEqualTo("white dwarf age: 4 years mass: 128 tons")
        star.timePasses()
        assertThat(star.toString()).isEqualTo("supernova age: 8 years mass: 1024 tons")
        star.timePasses()
        assertThat(star.toString()).isEqualTo("dead star age: 16 years mass: 8192 tons")
        star.timePasses()
        assertThat(star.toString()).isEqualTo("dead star age: 64 years mass: 0 tons")
        star.timePasses()
        assertThat(star.toString()).isEqualTo("dead star age: 256 years mass: 0 tons")
    }

    @Test
    fun `set memento test`() {
        val star = Star(StarType.SUN, 1, 2)
        val firstMemento = star.getMemento()
        assertThat(star.toString()).isEqualTo("sun age: 1 years mass: 2 tons")

        star.timePasses()
        val secondMemento = star.getMemento()
        assertThat(star.toString()).isEqualTo("red giant age: 2 years mass: 16 tons")

        star.timePasses()
        val thirdMemento = star.getMemento()
        assertThat(star.toString()).isEqualTo("white dwarf age: 4 years mass: 128 tons")

        star.timePasses()
        assertThat(star.toString()).isEqualTo("supernova age: 8 years mass: 1024 tons")

        star.setMemento(thirdMemento)
        assertThat(star.toString()).isEqualTo("white dwarf age: 4 years mass: 128 tons")

        star.timePasses()
        assertThat(star.toString()).isEqualTo("supernova age: 8 years mass: 1024 tons")

        star.setMemento(secondMemento)
        assertThat(star.toString()).isEqualTo("red giant age: 2 years mass: 16 tons")

        star.setMemento(firstMemento)
        assertThat(star.toString()).isEqualTo("sun age: 1 years mass: 2 tons")
    }
}
