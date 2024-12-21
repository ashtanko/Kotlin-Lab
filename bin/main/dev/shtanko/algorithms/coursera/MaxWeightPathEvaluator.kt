/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.algorithms.coursera

class MaxWeightPathEvaluator(private val weights: LongArray) {

    private val d = LongArray(weights.size)
    private val path = HashSet<Int>()
    private var maxPathWeight = -1L

    fun evaluate(): Long {
        if (maxPathWeight != -1L) {
            return maxPathWeight
        }

        d[0] = weights[0]
        if (weights.size == 1) {
            return weights[0]
        }

        d[1] = weights[1]
        for (i in 2 until weights.size) {
            val weight1 = d[i - 1]
            val weight2 = d[i - 2] + weights[i]
            if (weight1 > weight2) {
                d[i] = weight1
            } else {
                d[i] = weight2
            }
        }

        return d.last()
    }

    fun getPath(): Set<Int> {
        if (path.isNotEmpty()) {
            return path
        }

        if (maxPathWeight == -1L) {
            evaluate()
        }

        var i = d.lastIndex
        while (i >= 2) {
            when {
                d[i] == weights[i] + d[i - 2] -> {
                    path.add(i + 1)
                    i -= 2
                }

                d[i] == d[i - 1] -> --i
                else -> error("invalid state at $i")
            }
        }

        path.add(i + 1)
        return path
    }
}
