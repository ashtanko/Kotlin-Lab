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

package dev.shtanko.algorithms.leetcode

import kotlin.math.abs

/**
 * Calculates the minimum number of steps required to reach a target value.
 *
 * Given a target integer, this function calculates the minimum number of steps
 * needed to reach the target value. It employs a mathematical approach to determine
 * the steps required by considering the sum of consecutive integers.
 *
 * @param target The target integer to reach.
 * @return The minimum number of steps to reach the target value.
 */
fun reachNumber(target: Int): Int {
    // Calculate the absolute value of the target
    val t = abs(target)

    // Initialize variables to track steps and sum
    var step = 0
    var sum = 0

    // Increment steps and update sum until it reaches or exceeds the target
    while (sum < t) {
        step++
        sum += step
    }

    // Adjust steps and sum to reach the target with an even difference
    while ((sum - t) % 2 != 0) {
        step++
        sum += step
    }

    return step
}
