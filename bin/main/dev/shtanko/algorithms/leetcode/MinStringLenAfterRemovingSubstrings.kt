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

import java.util.Deque
import java.util.LinkedList

/**
 * 2696. Minimum String Length After Removing Substrings
 * @see <a href="https://leetcode.com/problems/minimum-string-length-after-removing-substrings/">Source</a>
 */
fun interface MinStringLenAfterRemovingSubstrings {
    operator fun invoke(str: String): Int
}

class MinStringLenReplace : MinStringLenAfterRemovingSubstrings {
    override fun invoke(str: String): Int {
        var processedString = str
        // Continue processing while "AB" or "CD" substrings exist
        while (processedString.contains("AB") || processedString.contains("CD")) {
            if (processedString.contains("AB")) {
                // Remove all occurrences of "AB"
                processedString = processedString.replace("AB", "")
            } else if (processedString.contains("CD")) {
                // Remove all occurrences of "CD"
                processedString = processedString.replace("CD", "")
            }
        }

        return processedString.length
    }
}

class MinStringLenStack : MinStringLenAfterRemovingSubstrings {
    override fun invoke(str: String): Int {
        val stack: Deque<Char> = LinkedList()

        // Iterate over each character in the input string
        for (char in str) {
            // If the stack is empty, simply push the current character
            if (stack.isEmpty()) {
                stack.push(char)
                continue
            }

            // Check for "AB" pattern, remove the pair by popping from the stack
            if (char == 'B' && stack.peek() == 'A') {
                stack.pop()
            }
            // Check for "CD" pattern, remove the pair by popping from the stack
            else if (char == 'D' && stack.peek() == 'C') {
                stack.pop()
            }
            // Otherwise, push the current character onto the stack
            else {
                stack.push(char)
            }
        }

        return stack.size
    }
}

class MinStringLenInPlace : MinStringLenAfterRemovingSubstrings {
    override fun invoke(str: String): Int {
        var writePtr = 0
        val charArray = str.toCharArray()

        // Iterate over each character in the string
        for (readPtr in charArray.indices) {
            // Write the current character to the current write position
            charArray[writePtr] = charArray[readPtr]

            // Check if we have a valid pattern to remove
            if (
                writePtr > 0 &&
                (charArray[writePtr - 1] == 'A' || charArray[writePtr - 1] == 'C') &&
                charArray[writePtr] == charArray[writePtr - 1] + 1
            ) {
                writePtr--
            } else {
                writePtr++ // No match, so move the write pointer forward
            }
        }

        // The writePtr now represents the length of the remaining string
        return writePtr
    }
}
