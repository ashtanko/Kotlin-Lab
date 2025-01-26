/*
 * Copyright 2024 Oleksii Shtanko
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
 * 3110. Score of a String
 * @see <a href="https://leetcode.com/problems/score-of-a-string/">Source</a>
 */
fun interface ScoreOfString {
    operator fun invoke(str: String): Int
}

/**
 * # Intuition
 * The goal is to compute the "score" of a string, defined as the sum of absolute differences between
 * the ASCII values of adjacent characters. This can be visualized as finding the "distance" between
 * each consecutive character and summing these distances.
 *
 * # Approach
 * - Iterate through the string and for each pair of consecutive characters, compute the absolute
 *   difference between their ASCII values.
 * - Use Kotlin's `zipWithNext` function to create pairs of consecutive characters.
 * - Apply a transformation on these pairs to calculate the absolute difference.
 * - Use `sum` to aggregate these differences into a final score.
 *
 * # Complexity
 * - Time complexity:
 *   The time complexity is $$O(n)$$, where $$n$$ is the length of the string. This is because we
 *   iterate through the string once to create pairs and once more to sum the differences.
 *
 * - Space complexity:
 *   The space complexity is $$O(n)$$ because `zipWithNext` generates a list of pairs which, in the
 *   worst case, will have size $$n-1$$. Additionally, the intermediate list of differences also
 *   requires $$O(n)$$ space.
 */
class ScoreOfStringIteration : ScoreOfString {
    override fun invoke(str: String) = str.zipWithNext { a, b -> kotlin.math.abs(a - b) }.sum()
}
