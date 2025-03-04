/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.extensions.swap

/**
 * Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
 * Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
 */
fun interface AbstractSortByParity {
    operator fun invoke(array: IntArray): IntArray
}

class SortByParityTwoPass : AbstractSortByParity {
    override operator fun invoke(array: IntArray): IntArray {
        val arraySize = array.size
        val resultArray = IntArray(arraySize)
        var evenIndex = 0
        var oddIndex = 1

        for (element in array) {
            if (element % 2 == 0) {
                resultArray[evenIndex] = element
                evenIndex += 2
            }
        }

        for (element in array) {
            if (element % 2 == 1) {
                resultArray[oddIndex] = element
                oddIndex += 2
            }
        }

        return resultArray
    }
}

class SortByParityHeads : AbstractSortByParity {
    override operator fun invoke(array: IntArray): IntArray {
        var oddIndex = 1
        var evenIndex = 0

        while (evenIndex < array.size) {
            if (array[evenIndex] % 2 == 1) {
                while (array[oddIndex] % 2 == 1) {
                    oddIndex += 2
                }
                array.swap(evenIndex, oddIndex)
            }
            evenIndex += 2
        }

        return array
    }
}

class SortByParityStraightForward : AbstractSortByParity {
    override operator fun invoke(array: IntArray): IntArray {
        val n = array.size
        var evenIndex = 0
        var oddIndex = 1

        while (evenIndex < n && oddIndex < n) {
            if (array[evenIndex] % 2 != 0) {
                array.swap(evenIndex, oddIndex)
                oddIndex += 2
            } else {
                evenIndex += 2
            }
        }

        return array
    }
}
