/*
 * Copyright 2025 Oleksii Shtanko
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

package dev.shtanko.kotlinlang.exhaustivenesschecks

@Suppress("EmptyFunctionBlock")
object ExhaustivenessChecks {
    @JvmStatic
    fun main(args: Array<String>) {

    }

    sealed class Vehicle

    data class Car(
        val manufacturer: String, val model: String,
    ) : Vehicle()

    data class Bicycle(
        val manufacturer: String,
    ) : Vehicle()

    fun getVehicle(vehicle: Vehicle) =
        when (vehicle) {
            is Car -> "${vehicle.manufacturer} - ${vehicle.model}"
            is Bicycle -> vehicle.manufacturer

            //Error
            //'when' expression must be exhaustive, add necessary 'else' branch
        }
}
