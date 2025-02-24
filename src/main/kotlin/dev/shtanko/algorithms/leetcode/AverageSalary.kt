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

import dev.shtanko.algorithms.annotations.BruteForce
import dev.shtanko.algorithms.annotations.StraightForward
import dev.shtanko.algorithms.annotations.level.Easy

/**
 * 1491. Average Salary Excluding the Minimum and Maximum Salary
 * @see <a href="https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary">
 *     Source</a>
 */
@Easy(link = "https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary")
fun interface AverageSalary {
    fun average(salaries: IntArray): Double
}

@BruteForce
class AverageSalaryBruteForce : AverageSalary {
    override fun average(salaries: IntArray): Double {
        var sum = 0.0
        var min = Int.MAX_VALUE
        var max = 0
        for (salary in salaries) {
            sum += salary
            if (salary < min) {
                min = salary
            }
            if (salary > max) {
                max = salary
            }
        }
        return sum.minus(max).minus(min) / salaries.size.minus(2)
    }
}

@StraightForward
class AverageSalarySimple : AverageSalary {
    override fun average(salaries: IntArray): Double {
        return salaries.sorted().toTypedArray().copyOfRange(1, salaries.size - 1).average()
    }
}
