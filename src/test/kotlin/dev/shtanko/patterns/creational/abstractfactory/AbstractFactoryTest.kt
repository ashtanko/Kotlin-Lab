/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.patterns.creational.abstractfactory

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AbstractFactoryTest {

    @Test
    fun `abstract orange factory test`() {
        val plantFactory = PlantFactory.createFactory<OrangePlant>()
        val plant = plantFactory.makePlant()
        assertThat(plant).isInstanceOf(OrangePlant::class.java)
    }

    @Test
    fun `abstract apple factory test`() {
        val plantFactory = PlantFactory.createFactory<ApplePlant>()
        val plant = plantFactory.makePlant()
        assertThat(plant).isInstanceOf(ApplePlant::class.java)
    }
}
