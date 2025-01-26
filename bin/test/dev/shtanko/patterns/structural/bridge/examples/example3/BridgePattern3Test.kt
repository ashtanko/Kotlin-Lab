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

package dev.shtanko.patterns.structural.bridge.examples.example3

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BridgePattern3Test {
    private val originalOut = System.out
    private val outputStream = ByteArrayOutputStream()

    @BeforeEach
    fun setUpStreams() {
        System.setOut(PrintStream(outputStream))
    }

    @Test
    fun testEcoCar() {
        val ecoCar = Car(EcoMode())
        ecoCar.drive()

        val expectedOutput = "Driving the car\nApplying Eco mode\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testSportBike() {
        val sportBike = Bike(SportMode())
        sportBike.drive()

        val expectedOutput = "Riding the bike\nApplying Sport mode\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testEcoBike() {
        val ecoBike = Bike(EcoMode())
        ecoBike.drive()

        val expectedOutput = "Riding the bike\nApplying Eco mode\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testSportCar() {
        val sportCar = Car(SportMode())
        sportCar.drive()

        val expectedOutput = "Driving the car\nApplying Sport mode\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testMultipleVehicles() {
        val ecoCar = Car(EcoMode())
        val sportBike = Bike(SportMode())

        ecoCar.drive()
        sportBike.drive()

        val expectedOutput = "Driving the car\nApplying Eco mode\n" +
            "Riding the bike\nApplying Sport mode\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testMultipleModes() {
        val sportCar = Car(SportMode())
        val ecoBike = Bike(EcoMode())

        sportCar.drive()
        ecoBike.drive()

        val expectedOutput = "Driving the car\nApplying Sport mode\n" +
            "Riding the bike\nApplying Eco mode\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    // Restore the original System.out
    @AfterEach
    fun restoreStreams() {
        System.setOut(originalOut)
    }
}
