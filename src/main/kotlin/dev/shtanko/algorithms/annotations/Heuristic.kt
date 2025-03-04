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

package dev.shtanko.algorithms.annotations

/**
 * The Heuristic approach is used to find approximate solutions to optimization problems or decision-making tasks
 * when an exact solution is difficult or time-consuming to compute. Heuristic methods guide the search for solutions
 * by applying practical rules, strategies, or approximations that lead to good-enough results in a reasonable amount
 * of time.
 *
 * # How It Works
 *
 * 1: Heuristic Functions:
 * A heuristic function estimates the cost or value of a solution or state in a problem. It is often used in search
 * algorithms like A* or greedy algorithms to prioritize which paths or choices to explore.
 * For example, in pathfinding, the heuristic function may estimate the distance from a node to the target, guiding
 * the search towards the goal.
 *
 * 2: Greedy Approaches:
 * Some heuristics involve making locally optimal choices at each step, hoping that these choices will lead to a
 * globally optimal solution. Greedy algorithms are a common heuristic technique, such as in the case of the fractional
 * knapsack problem.
 *
 * 3: Approximation:
 * Heuristic methods often provide approximations or estimates that are "good enough" for practical use, even if they
 * don't guarantee the best possible result. This is particularly useful in large, complex problems with a vast search
 * space where exhaustive exploration is impractical.
 *
 * # Example:
 * In pathfinding algorithms like A*, the heuristic is typically a distance estimate (like the Euclidean or Manhattan
 * distance) from the current node to the goal node. This helps the algorithm prioritize paths that appear to lead more
 * directly towards the goal.
 *
 * # Advantages:
 * * Faster Solutions: Heuristic methods can often find good solutions much faster than exact methods, especially in
 * complex or large problems.
 * * Useful for Large Search Spaces: Heuristics are particularly helpful when the solution space is vast, and exhaustive
 * search isn't feasible.
 * * Flexibility: Heuristic methods can often be customized to suit different problem domains or requirements.
 *
 * # Disadvantages:
 * * Approximate Solutions: Heuristic methods typically do not guarantee the best solution, and the results may vary
 * depending on the heuristic used.
 * * Problem-Specific: A heuristic that works well for one problem may not be effective for another, requiring
 * customization or adaptation.
 *
 * # Common Applications:
 * - Pathfinding algorithms (e.g., A*, Dijkstra's algorithm with heuristic guidance)
 * - Game AI (e.g., decision-making in board games or strategy games)
 * - Optimization problems (e.g., traveling salesman problem, knapsack problem)
 * - Machine learning and artificial intelligence (e.g., decision trees, reinforcement learning)
 *
 * @property info An optional string that provides additional information about the Heuristic approach implementation
 * or usage.
 * @constructor Creates a new Heuristic annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class Heuristic(val info: String = "")
