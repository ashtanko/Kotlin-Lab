/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.patterns.structural.adapter.example3

// Adapter class that converts Celsius measurements to Fahrenheit
class TemperatureAdapter(private val sensor: TemperatureSensor) : TemperatureSystem {
    companion object {
        const val CELSIUS_TO_FAHRENHEIT_RATIO = 9.0 / 5.0
        const val FAHRENHEIT_OFFSET = 32.0
    }

    override fun getTemperatureInFahrenheit(): Double {
        val celsiusTemperature = sensor.getTemperatureInCelsius()
        return celsiusTemperature * CELSIUS_TO_FAHRENHEIT_RATIO + FAHRENHEIT_OFFSET
    }
}
