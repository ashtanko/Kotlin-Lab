/*
 * Copyright 2023 Oleksii Shtanko
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

import dev.shtanko.algorithms.DECIMAL
import dev.shtanko.algorithms.annotations.OnePass
import dev.shtanko.algorithms.annotations.StraightForward
import dev.shtanko.algorithms.annotations.level.Easy
import java.util.LinkedList

/**
 * 989. Add to Array-Form of Integer
 * @see <a href="https://leetcode.com/problems/add-to-array-form-of-integer/">Source</a>
 */
@Easy(link = "https://leetcode.com/problems/add-to-array-form-of-integer")
fun interface AddToArrayForm {
    operator fun invoke(num: IntArray, k: Int): List<Int>
}

@StraightForward
class AddToArrayFormSimple : AddToArrayForm {
    /**
     * Adds an integer value to each element of an array of integers using a specified base.
     *
     * @param num The array of integers to be modified.
     * @param k The integer value to be added to each element.
     * @return A list of integers representing the modified array.
     */
    override operator fun invoke(num: IntArray, k: Int): List<Int> {
        val res: MutableList<Int> = LinkedList()
        var k0 = k
        for (i in num.size - 1 downTo 0) {
            res.add(0, (num[i] + k0) % DECIMAL)
            k0 = (num[i] + k0) / DECIMAL
        }
        while (k0 > 0) {
            res.add(0, k0 % DECIMAL)
            k0 /= DECIMAL
        }
        return res
    }
}

@OnePass
class AddToArrayFormOnePass : AddToArrayForm {
    /**
     * Calculates the result of adding an integer to an array of integers in decimal form.
     *
     * @param num The array of integers representing the decimal form.
     * @param k The integer to be added.
     * @return The result of adding the integer to the array of integers.
     */
    override operator fun invoke(num: IntArray, k: Int): List<Int> {
        val res: MutableList<Int> = LinkedList()
        var i: Int = num.size - 1
        var k0 = k
        while (i >= 0 || k0 > 0) {
            res.add(0, (if (i >= 0) num[i] + k0 else k0) % DECIMAL)
            k0 = (if (i >= 0) num[i] + k0 else k0) / DECIMAL
            --i
        }
        return res
    }
}
