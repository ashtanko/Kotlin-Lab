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

package dev.shtanko.algorithms.annotations

/**
 * The Simulated Annealing algorithm is a probabilistic optimization technique inspired by the process of annealing in
 * metallurgy.
 * It is used to find an approximate solution to an optimization problem, particularly in cases where the solution space
 * is large and complex.
 *
 * # How It Works
 *
 * Simulated Annealing is based on the concept of heating a material and then slowly lowering the temperature to
 * minimize the system's energy.
 * Similarly, in the algorithm, a solution starts with an initial guess and is iteratively modified. During each
 * iteration, a new solution is proposed, and if the new solution improves the objective function, it is accepted.
 * If the new solution is worse, it is accepted with a probability that decreases over time, allowing the algorithm to
 * escape local optima.
 *
 * 1. **Initial Solution:** Start with an initial solution and an initial temperature.
 * 2. **Neighbor Solution Generation:** Randomly generate a neighboring solution from the current one.
 * 3. **Acceptance Probability:** If the neighbor improves the objective function, accept it. If it worsens, accept it
 * with a probability based on the temperature.
 * 4. **Cooling Schedule:** Gradually reduce the temperature over time.
 * 5. **Termination:** Repeat the process until the temperature is low enough or a stopping condition is met.
 *
 * # Mathematical Formulation
 * The acceptance probability for a worse solution is given by:
 * ```
 * P(accept) = exp((currentEnergy - neighborEnergy) / temperature)
 * ```
 * where `currentEnergy` is the energy of the current solution, `neighborEnergy` is the energy of the proposed solution,
 * and `temperature` is the current temperature of the system.
 *
 * # Time Complexity
 * - **Worst-case Complexity:** O(n * t), where n is the number of iterations and t is the number of temperature steps.
 * - **Best-case Complexity:** The complexity can vary depending on the cooling schedule and the nature of the problem.
 *
 * # Example:
 * Consider a traveling salesman problem (TSP) where the objective is to find the shortest possible route visiting a
 * set of cities.
 * - Start with a random tour and compute its length.
 * - At each step, generate a neighboring tour by swapping two cities.
 * - Accept the new tour based on the temperature and the change in tour length, iterating until the temperature is
 * sufficiently low.
 *
 * # Advantages:
 * * Global Optimization: Simulated Annealing has the ability to escape local optima, potentially finding a global
 * optimum.
 * * Flexible: It can be applied to a wide range of optimization problems, including NP-hard problems like the traveling
 * salesman problem.
 * * Parallelizable: The algorithm can be parallelized to explore different parts of the solution space simultaneously.
 *
 * # Disadvantages:
 * * Requires careful tuning: The performance of Simulated Annealing depends heavily on the cooling schedule and
 * acceptance probability.
 * * Slow convergence: The algorithm can be slow, especially for problems with large solution spaces.
 * * Randomness: Since the algorithm is probabilistic, it may not always find the optimal solution in a reasonable
 * amount of time.
 *
 * # Applications:
 * - Traveling Salesman Problem (TSP)
 * - Job scheduling and resource allocation problems
 * - Combinatorial optimization problems in manufacturing, logistics, and design
 * - Machine learning hyperparameter tuning
 *
 * @property info An optional string that provides additional information about the Simulated Annealing algorithm
 * implementation or usage.
 * @constructor Creates a new SimulatedAnnealing annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class SimulatedAnnealing(val info: String = "")
