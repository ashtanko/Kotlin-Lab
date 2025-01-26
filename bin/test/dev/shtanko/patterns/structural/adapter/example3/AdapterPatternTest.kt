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

package dev.shtanko.patterns.structural.adapter.example3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AdapterPatternTest {

    @Test
    fun `temperature adapter test`() {
        val mockSensor = MockTemperatureSensor()
        val adapter = TemperatureAdapter(mockSensor)
        val client = TemperatureClient(adapter)

        val expectedFahrenheit =
            mockSensor.getTemperatureInCelsius() * TemperatureAdapter.CELSIUS_TO_FAHRENHEIT_RATIO + TemperatureAdapter.FAHRENHEIT_OFFSET

        val output = captureOutput {
            client.displayTemperature()
        }

        val expectedOutput = "Temperature in Fahrenheit: $expectedFahrenheit°F\n"
        assertEquals(expectedOutput, output)
    }

    private fun captureOutput(block: () -> Unit): String {
        val originalOut = System.out
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))
        block()
        System.setOut(originalOut)
        return outputStream.toString()
    }
}

class MockTemperatureSensor : TemperatureSensor() {
    override fun getTemperatureInCelsius(): Double {
        return 20.0 // Simulate a different temperature for testing
    }
}
