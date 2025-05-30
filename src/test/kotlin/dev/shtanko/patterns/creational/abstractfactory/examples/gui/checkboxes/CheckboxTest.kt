/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.patterns.creational.abstractfactory.examples.gui.checkboxes

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CheckboxTest {
    @Test
    fun `paint mac os checkbox test`() {
        Assertions.assertThat(MacOSCheckbox().paint()).isEqualTo("You have created MacOSCheckbox.")
    }

    @Test
    fun `paint windows checkbox test`() {
        Assertions.assertThat(WindowsCheckbox().paint()).isEqualTo("You have created WindowsCheckbox.")
    }
}
