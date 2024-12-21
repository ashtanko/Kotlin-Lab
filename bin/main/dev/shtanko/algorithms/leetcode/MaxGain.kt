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

import java.util.Stack

/**
 * 1717. Maximum Score From Removing Substrings
 * @see <a href="https://leetcode.com/problems/maximum-score-from-removing-substrings">Source</a>
 */
fun interface MaxGain {
    operator fun invoke(str: String, x: Int, y: Int): Int
}

class MaxGainStack : MaxGain {
    override fun invoke(str: String, x: Int, y: Int): Int {
        var totalScore = 0
        val highPriorityPair = if (x > y) "ab" else "ba"
        val lowPriorityPair = if (highPriorityPair == "ab") "ba" else "ab"

        // First pass: remove high priority pair
        val stringAfterFirstPass = removeSubstring(str, highPriorityPair)
        var removedPairsCount = (str.length - stringAfterFirstPass.length) / 2

        // Calculate score from first pass
        totalScore += removedPairsCount * maxOf(x, y)

        // Second pass: remove low priority pair
        val stringAfterSecondPass = removeSubstring(stringAfterFirstPass, lowPriorityPair)
        removedPairsCount = (stringAfterFirstPass.length - stringAfterSecondPass.length) / 2

        // Calculate score from second pass
        totalScore += removedPairsCount * minOf(x, y)

        return totalScore
    }

    private fun removeSubstring(input: String, targetPair: String): String {
        val charStack = Stack<Char>()

        // Iterate through each character in the input string
        for (currentChar in input) {
            // Check if current character forms the target pair with the top of the stack
            if (currentChar == targetPair[1] && charStack.isNotEmpty() && charStack.peek() == targetPair[0]) {
                charStack.pop() // Remove the matching character from the stack
            } else {
                charStack.push(currentChar)
            }
        }

        // Reconstruct the remaining string after removing target pairs
        val remainingChars = StringBuilder()
        while (charStack.isNotEmpty()) {
            remainingChars.append(charStack.pop())
        }
        return remainingChars.reverse().toString()
    }
}

class MaxGainGreedy : MaxGain {
    override fun invoke(str: String, x: Int, y: Int): Int {
        val text = StringBuilder(str)
        var totalPoints = 0

        if (x > y) {
            // Remove "ab" first (higher points), then "ba"
            totalPoints += removeSubstring(text, "ab", x)
            totalPoints += removeSubstring(text, "ba", y)
        } else {
            // Remove "ba" first (higher or equal points), then "ab"
            totalPoints += removeSubstring(text, "ba", y)
            totalPoints += removeSubstring(text, "ab", x)
        }

        return totalPoints
    }

    private fun removeSubstring(inputString: StringBuilder, targetSubstring: String, pointsPerRemoval: Int): Int {
        var totalPoints = 0
        var writeIndex = 0

        // Iterate through the string
        for (readIndex in inputString.indices) {
            // Add the current character
            inputString.setCharAt(writeIndex++, inputString[readIndex])

            // Check if we've written at least two characters and they match the target substring
            if (writeIndex > 1 &&
                inputString[writeIndex - 2] == targetSubstring[0] &&
                inputString[writeIndex - 1] == targetSubstring[1]
            ) {
                writeIndex -= 2 // Move write index back to remove the match
                totalPoints += pointsPerRemoval
            }
        }

        // Trim the StringBuilder to remove any leftover characters
        inputString.setLength(writeIndex)

        return totalPoints
    }
}

class MaxGainCounting : MaxGain {
    override fun invoke(str: String, x: Int, y: Int): Int {
        var pointsForAb = x
        var pointsForBa = y
        var input = str
        // Ensure "ab" always has higher points than "ba"
        if (pointsForAb < pointsForBa) {
            // Swap points
            val temp = pointsForAb
            pointsForAb = pointsForBa
            pointsForBa = temp
            // Reverse the string to maintain logic
            input = input.reversed()
        }

        var countA = 0
        var countB = 0
        var totalPoints = 0

        for (char in input) {
            when (char) {
                'a' -> countA++
                'b' -> {
                    if (countA > 0) {
                        // Can form "ab", remove it and add points
                        countA--
                        totalPoints += pointsForAb
                    } else {
                        // Can't form "ab", keep 'b' for potential future "ba"
                        countB++
                    }
                }

                else -> {
                    // Non 'a' or 'b' character encountered
                    // Calculate points for any remaining "ba" pairs
                    totalPoints += minOf(countB, countA) * pointsForBa
                    // Reset counters for next segment
                    countA = 0
                    countB = 0
                }
            }
        }

        // Calculate points for any remaining "ba" pairs at the end
        totalPoints += minOf(countB, countA) * pointsForBa

        return totalPoints
    }
}
