/*
 * Copyright 2022 Oleksii Shtanko
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
 * This object contains a function to implement the strStr() function.
 * The strStr() function finds the first occurrence of a substring (needle) in a string (haystack).
 * It returns the index of the first occurrence of the needle in the haystack, or -1 if the needle is not part of
 * the haystack.
 */
object StrStr {
    /**
     * This function is an operator function that invokes the solve function with the given haystack and needle.
     * @param haystack The string in which to find the needle.
     * @param needle The substring to find in the haystack.
     * @return The index of the first occurrence of the needle in the haystack, or -1 if the needle is not part of
     * the haystack.
     */
    operator fun invoke(haystack: String, needle: String) = solve(haystack, needle)

    /**
     * This function is a recursive function that finds the first occurrence of a substring (needle) in a string
     * (haystack).
     * It checks if the haystack starts with the needle. If it does, it returns the current count. If it doesn't,
     * it drops the first character of the haystack and increments the count.
     * @param stack The string in which to find the needle.
     * @param needle The substring to find in the haystack.
     * @param count The current index in the haystack.
     * @return The index of the first occurrence of the needle in the haystack, or -1 if the needle is not part of
     * the haystack.
     */
    private tailrec fun solve(stack: String, needle: String, count: Int = 0): Int =
        when {
            stack.length < needle.length -> -1
            stack.startsWith(needle) -> count
            else -> solve(stack.drop(1), needle, count + 1)
        }
}
