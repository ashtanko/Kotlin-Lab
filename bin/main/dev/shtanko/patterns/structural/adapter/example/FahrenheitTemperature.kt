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

class FahrenheitTemperature(private var celsiusTemperature: CelsiusTemperature) : Temperature {

    companion object {
        private const val RISE = 100
        private const val RATE = 180
        private const val SCALE = 32
    }

    override var temperature: Double
        get() = convertCelsiusToFahrenheit(celsiusTemperature.temperature)
        set(temperatureInF) {
            celsiusTemperature.temperature = convertFahrenheitToCelsius(temperatureInF)
        }

    private fun convertFahrenheitToCelsius(f: Double): Double {
        val scaleResult = f - SCALE
        return scaleResult * RISE / RATE
    }

    private fun convertCelsiusToFahrenheit(c: Double): Double {
        val rateRiseResult = c * RATE / RISE
        return rateRiseResult + SCALE
    }
}
