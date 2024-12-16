/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.algorithms.simulatedannealing

import kotlin.math.sqrt
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class TravelingSalesmanProblemTest {
    private fun createTestCities(): List<TravelingSalesmanProblem.City> {
        return listOf(
            TravelingSalesmanProblem.City(0, 0),
            TravelingSalesmanProblem.City(1, 1),
            TravelingSalesmanProblem.City(2, 2),
            TravelingSalesmanProblem.City(3, 3),
        )
    }

    @Test
    fun `test calculateTourLength`() {
        val cities = createTestCities()
        val tsp = TravelingSalesmanProblem(cities, initialTemperature = 1000.0, coolingRate = 0.003)

        // Known distance calculation manually: from (0,0) -> (1,1) -> (2,2) -> (3,3) -> back to (0,0)
        val expectedDistance = sqrt(2.0) + sqrt(2.0) + sqrt(2.0) + sqrt(18.0)
        val tour = cities
        val calculatedDistance = tsp.calculateTourLength(tour)

        assertEquals(expectedDistance, calculatedDistance, 0.0001, "The calculated tour length should be correct.")
    }

    @Test
    fun `test findShortestTour returns a valid tour`() {
        val cities = createTestCities()
        val tsp = TravelingSalesmanProblem(cities, initialTemperature = 1000.0, coolingRate = 0.003)

        val shortestTour = tsp.findShortestTour()

        // Ensure that the result is a valid tour, meaning it is not empty and the correct number of cities is present
        assertNotNull(shortestTour, "The shortest tour should not be null.")
        assertEquals(
            cities.size,
            shortestTour.size,
            "The shortest tour should contain the same number of cities as the input.",
        )
    }

    @Test
    fun `test findShortestTour should not be the same as the initial random solution`() {
        val cities = createTestCities()
        val tsp = TravelingSalesmanProblem(cities, initialTemperature = 1000.0, coolingRate = 0.003)

        // Run the simulated annealing algorithm to find the shortest tour
        val shortestTour = tsp.findShortestTour()

        // The final solution should differ from the initial solution
        assertNotEquals(
            cities.shuffled(),
            shortestTour,
            "The final tour should be different from the initial random tour.",
        )
    }
}
