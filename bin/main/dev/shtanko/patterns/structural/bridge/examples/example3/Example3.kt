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

// Implementation hierarchy: Driving Modes
fun interface DrivingMode {
    fun applyMode()
}

class EcoMode : DrivingMode {
    override fun applyMode() {
        println("Applying Eco mode")
    }
}

class SportMode : DrivingMode {
    override fun applyMode() {
        println("Applying Sport mode")
    }
}

// Abstraction hierarchy: Vehicles
abstract class Vehicle(protected val drivingMode: DrivingMode) {
    abstract fun drive()
}

class Car(drivingMode: DrivingMode) : Vehicle(drivingMode) {
    override fun drive() {
        println("Driving the car")
        drivingMode.applyMode()
    }
}

class Bike(drivingMode: DrivingMode) : Vehicle(drivingMode) {
    override fun drive() {
        println("Riding the bike")
        drivingMode.applyMode()
    }
}

fun main() {
    val ecoCar = Car(EcoMode())
    val sportBike = Bike(SportMode())

    ecoCar.drive()
    sportBike.drive()
}
