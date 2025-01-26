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
 * Subdomain Visit Count
 * This extension function calculates the subdomain visit count from an array of strings.
 * Each string in the array represents a domain with the number of visits.
 * The function returns a list of strings where each string represents a subdomain with the total number of visits.
 *
 * @receiver Array<String> The array of strings where each string represents a domain with the number of visits.
 * @return List<String> The list of strings where each string represents a subdomain with the total number of visits.
 */
fun Array<String>.subdomainVisits(): List<String> {
    // Initialize the result list and the map to store the frequency of each subdomain
    val result: MutableList<String> = ArrayList()
    val map: MutableMap<String, Int> = HashMap()

    // Initialize the StringBuilder to build the subdomains
    val resultStringBuilder = StringBuilder()

    // Iterate over each domain in the array
    for (cpDomain in this) {
        // Extract the number of visits and the domain from the string
        val indexSpace = cpDomain.indexOf(' ')
        val numClicks = cpDomain.substring(0, indexSpace).toInt()
        val domain = cpDomain.substring(indexSpace + 1)

        // Reset the StringBuilder and append the domain
        resultStringBuilder.setLength(0)
        resultStringBuilder.append(domain)

        // Iterate over each subdomain in the domain
        while (true) {
            // Update the frequency of the subdomain in the map
            map[resultStringBuilder.toString()] = map.getOrDefault(resultStringBuilder.toString(), 0) + numClicks

            // Find the next subdomain
            val dotPosition = resultStringBuilder.indexOf(".")
            if (dotPosition == -1) break
            resultStringBuilder.delete(0, dotPosition + 1)
        }
    }

    // Add the frequency of each subdomain to the result list
    for (domain in map.keys) result.add(map[domain].toString() + " " + domain)

    // Return the result list
    return result
}
