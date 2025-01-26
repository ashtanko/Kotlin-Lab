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

/**
 * This function calculates the sum of even numbers after performing queries on an input array.
 * Each query consists of two integers where the first integer is the value to be added to the element at the index of
 * the second integer.
 * After each query, the sum of even numbers in the array is calculated and added to the result array.
 *
 * @param inputArray The input array on which the queries are to be performed.
 * @param queries An array of queries where each query is an array of two integers.
 * @return An array where each element is the sum of even numbers in the input array after each query.
 */
fun calculateSumEvenAfterQueries(inputArray: IntArray, queries: Array<IntArray>): IntArray {
    if (inputArray.isEmpty() || queries.isEmpty()) return intArrayOf()
    // Initialize the sum of even numbers in the input array
    var sumOfEvens = 0
    for (element in inputArray) if (element % 2 == 0) sumOfEvens += element

    // Initialize the result array with the size equal to the number of queries
    val resultArray = IntArray(queries.size)

    // Perform each query on the input array
    for (i in queries.indices) {
        // Extract the value and index from the current query
        val queryValue = queries[i][0]
        val queryIndex = queries[i][1]

        // If the element at the query index is even, subtract it from the sum of evens
        if (inputArray[queryIndex] % 2 == 0) sumOfEvens -= inputArray[queryIndex]

        // Add the query value to the element at the query index
        inputArray[queryIndex] += queryValue

        // If the updated element at the query index is even, add it to the sum of evens
        if (inputArray[queryIndex] % 2 == 0) sumOfEvens += inputArray[queryIndex]

        // Add the current sum of evens to the result array
        resultArray[i] = sumOfEvens
    }

    // Return the result array
    return resultArray
}
