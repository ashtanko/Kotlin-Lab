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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Medium
import java.util.PriorityQueue

/**
 * 1792. Maximum Average Pass Ratio
 * @see <a href="https://leetcode.com/problems/maximum-average-pass-ratio/">Source</a>
 */
@Medium("https://leetcode.com/problems/maximum-average-pass-ratio")
sealed interface MaxAverageRatio {
    operator fun invoke(classes: Array<IntArray>, extraStudents: Int): Double

    data object PriorityQueueStrategy : MaxAverageRatio {
        override fun invoke(classes: Array<IntArray>, extraStudents: Int): Double {
            // Lambda to calculate the gain of adding an extra student
            val maxHeap = PriorityQueue<DoubleArray> { a, b ->
                b[0].compareTo(a[0])
            }

            for (singleClass in classes) {
                val passes = singleClass[0]
                val totalStudents = singleClass[1]
                val gain = calculateGain(passes, totalStudents)
                maxHeap.offer(doubleArrayOf(gain, passes.toDouble(), totalStudents.toDouble()))
            }

            // Distribute extra students
            var remainingStudents = extraStudents
            while (remainingStudents-- > 0) {
                val current = maxHeap.poll()
                val passes = current[1].toInt()
                val totalStudents = current[2].toInt()
                maxHeap.offer(
                    doubleArrayOf(
                        calculateGain(passes + 1, totalStudents + 1),
                        (passes + 1).toDouble(),
                        (totalStudents + 1).toDouble(),
                    ),
                )
            }

            // Calculate the final average pass ratio
            var totalPassRatio = 0.0
            while (maxHeap.isNotEmpty()) {
                val current = maxHeap.poll()
                val passes = current[1].toInt()
                val totalStudents = current[2].toInt()
                totalPassRatio += passes.toDouble() / totalStudents
            }

            return totalPassRatio / classes.size
        }

        private fun calculateGain(passes: Int, totalStudents: Int): Double {
            return (passes + 1).toDouble() / (totalStudents + 1) - passes.toDouble() / totalStudents
        }
    }
}
