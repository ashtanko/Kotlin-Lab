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

package dev.shtanko.patterns.structural.adapter.example

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

internal class TemperatureTest {

    private val celsiusTemperature = CelsiusTemperature(0.0)
    private val fahrenheitTemperature = FahrenheitTemperature(celsiusTemperature)

    @Test
    internal fun `celsius to fahrenheit test`() {
        celsiusTemperature.temperature = 36.6
        val actual = fahrenheitTemperature.temperature
        val expected = 97.88
        assertThat(actual, equalTo(expected))
    }

    @Test
    internal fun `fahrenheit to celsius test`() {
        fahrenheitTemperature.temperature = 100.0
        val actual = celsiusTemperature.temperature
        val expected = 37.77777777777778
        assertThat(actual, equalTo(expected))
    }
}
