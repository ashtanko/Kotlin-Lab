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
 * # Backtracking
 *
 * Backtracking is a problem-solving algorithm that builds incrementally towards a solution. It explores all possible
 * candidates for the solution and abandons a candidate ("backtracks") as soon as it determines that this candidate
 * cannot be extended to a valid solution.
 *
 * ## How It Works
 *
 * 1. **Start with an empty solution**: Begin with an empty or partial solution to the problem.
 *
 * 2. **Extend the solution**: Add a new element to the current solution, ensuring that it still adheres to the
 * constraints of the problem.
 *
 * 3. **Check if the solution is valid**: Determine whether the current solution meets the criteria or constraints of
 * the problem.
 *
 * 4. **Recursive exploration**:
 *    - If the solution is valid, recursively attempt to extend the solution further.
 *    - If the solution is invalid, or if no further extension is possible, remove the last added element (backtrack)
 *    and try the next possible option.
 *
 * 5. **Repeat**: Continue this process until all possibilities have been explored or a solution is found.
 *
 * ## Example: N-Queens Problem
 *
 * The N-Queens problem is a classic example of backtracking. The task is to place `N` queens on an `N x N` chessboard
 * such that no two queens threaten each other.
 *
 * ### Steps:
 *
 * 1. **Start with an empty board**.
 * 2. **Place a queen in the first column of the first row**.
 * 3. **Move to the next row** and attempt to place a queen in a valid column (one that is not under attack).
 * 4. **If placement is valid**, recursively move to the next row and repeat the process.
 * 5. **If placing a queen leads to a conflict or no valid placements** for subsequent rows, **remove the queen
 * (backtrack)** and try the next column in the current row.
 * 6. **Continue until a solution is found** or all possibilities have been exhausted.
 *
 * ## Complexity
 *
 * - **Time Complexity**: The time complexity can be exponential, especially for large problem sizes. For example,
 * solving the N-Queens problem has a complexity of O(N!).
 * - **Space Complexity**: Space complexity mainly depends on the recursion stack and the storage of the board or state.
 *
 * Backtracking is particularly useful for problems where the solution space is too large to be explored exhaustively
 * but where a valid solution can be incrementally constructed.
 *
 * ### Example
 *
 * **Input**: Set = `{3, 34, 4, 12, 5, 2}`, Target Sum = `9`
 *
 * **Output**: Subsets that sum up to `9` are `[{3, 4, 2}, {3, 5, 1}, {4, 5}]`.
 *
 * ### Algorithm
 *
 * 1. **Start with an empty subset**.
 * 2. **Try adding elements from the set to the current subset**.
 * 3. **Check if the sum of the current subset equals the target**:
 *    - If yes, record this subset as a valid solution.
 *    - If not, continue adding more elements.
 * 4. **If adding an element causes the sum to exceed the target or if all elements are tried**, remove the last added
 * element (backtrack) and try the next possibility.
 *
 * ### Code Example (Kotlin)
 *
 * ```kotlin
 * fun findSubsets(nums: IntArray, target: Int): List<List<Int>> {
 *     val result = mutableListOf<List<Int>>()
 *
 *     fun backtrack(start: Int, currentSubset: MutableList<Int>, currentSum: Int) {
 *         if (currentSum == target) {
 *             result.add(ArrayList(currentSubset))
 *             return
 *         }
 *         if (currentSum > target) {
 *             return
 *         }
 *         for (i in start until nums.size) {
 *             currentSubset.add(nums[i])
 *             backtrack(i + 1, currentSubset, currentSum + nums[i])
 *             currentSubset.removeAt(currentSubset.size - 1)
 *         }
 *     }
 *
 *     backtrack(0, mutableListOf(), 0)
 *     return result
 * }
 * ```
 *
 * @property info An optional string that provides additional information about the backtracking algorithm.
 * @constructor Creates a new Backtracking annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class Backtracking(val info: String = "")
