/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.algorithms.simulatedannealing

import dev.shtanko.algorithms.annotations.SimulatedAnnealing
import kotlin.math.exp
import kotlin.math.sqrt
import kotlin.random.Random

/**
 * The Traveling Salesman Problem (TSP) is a classic optimization problem in computer science and operations research.
 * Given a list of cities and the distances between them, the goal is to find the shortest possible route that visits
 * each city exactly once and returns to the origin city.
 * The problem is known to be NP-hard, meaning that no efficient algorithm exists to solve it exactly for large inputs.
 * Simulated Annealing is a popular heuristic algorithm used to find approximate solutions to combinatorial optimization
 * problems like the TSP.
 * This implementation demonstrates how to use Simulated Annealing to solve the Traveling Salesman Problem.
 *
 * @property cities A list of cities represented by their (x, y) coordinates.
 * @property initialTemperature The initial temperature for the simulated annealing algorithm.
 * @property coolingRate The rate at which the temperature is reduced during the cooling process.
 * @constructor Creates a new TravelingSalesmanProblem instance with the given list of cities, initial temperature, and
 * cooling rate.
 * @see City
 * @see findShortestTour
 * @see calculateTourLength
 * @see generateNeighbor
 * @see [SimulatedAnnealing](https://en.wikipedia.org/wiki/Simulated_annealing)
 */
@SimulatedAnnealing
class TravelingSalesmanProblem(
    private val cities: List<City>,
    private val initialTemperature: Double,
    private val coolingRate: Double,
) {

    // Function to calculate the total distance of a given tour (sequence of cities)
    fun calculateTourLength(tour: List<City>): Double {
        var totalDistance = 0.0
        for (i in 0 until tour.size - 1) {
            val dx = tour[i + 1].x - tour[i].x
            val dy = tour[i + 1].y - tour[i].y
            totalDistance += sqrt((dx * dx + dy * dy).toDouble())
        }
        // Return to the start city
        val dx = tour.last().x - tour.first().x
        val dy = tour.last().y - tour.first().y
        totalDistance += sqrt((dx * dx + dy * dy).toDouble())
        return totalDistance
    }

    // Function to generate a neighbor tour by swapping two cities in the current tour
    fun generateNeighbor(tour: List<City>): List<City> {
        val newTour = tour.toMutableList()
        val i = Random.nextInt(newTour.size)
        val j = Random.nextInt(newTour.size)
        newTour[i] = tour[j]
        newTour[j] = tour[i]
        return newTour
    }

    // Simulated Annealing algorithm
    fun findShortestTour(): List<City> {
        var currentSolution = cities.shuffled() // Random initial tour
        var bestSolution = currentSolution
        var currentTemperature = initialTemperature
        var bestDistance = calculateTourLength(bestSolution)

        // Repeat the process until the temperature is very low
        while (currentTemperature > 1) {
            // Generate a neighboring solution by swapping two cities
            val neighbor = generateNeighbor(currentSolution)
            val currentDistance = calculateTourLength(currentSolution)
            val neighborDistance = calculateTourLength(neighbor)

            // If the neighbor is better or accepted with a probability, update the current solution
            if (neighborDistance < currentDistance ||
                Random.nextDouble() < exp((currentDistance - neighborDistance) / currentTemperature)
            ) {
                currentSolution = neighbor
            }

            // If the new solution is the best found so far, update the best solution
            if (calculateTourLength(currentSolution) < bestDistance) {
                bestSolution = currentSolution
                bestDistance = calculateTourLength(bestSolution)
            }

            // Cool down the temperature
            currentTemperature *= 1 - coolingRate
        }

        return bestSolution
    }

    data class City(val x: Int, val y: Int)
}
