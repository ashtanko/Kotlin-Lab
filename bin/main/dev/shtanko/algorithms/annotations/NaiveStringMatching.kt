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

package dev.shtanko.algorithms.annotations

/**
 * The Naive String Matching algorithm is a straightforward approach for finding the occurrences of a pattern within a
 * text string.
 * It checks all possible positions of the pattern within the text, comparing the characters one by one at each
 * position.
 *
 * # How It Works
 *
 * The algorithm works by sliding the pattern over the text, starting from each position of the text and comparing the
 * substring of the text with the pattern. If the characters match at every position, the pattern is found at that
 * position.
 * The process repeats for all possible starting positions in the text.
 *
 * Given a text `T` of length `n` and a pattern `P` of length `m`, the Naive String Matching algorithm checks every
 * possible position of the pattern in the text:
 *
 * - For each position `i` in the text from `0` to `n - m`, compare the substring `T[i, i + m - 1]` with `P`.
 * - If all characters match, the pattern is found at index `i` in the text.
 *
 * This approach is very simple but can be inefficient for large texts or patterns.
 *
 * # Time Complexity
 * The time complexity of the Naive String Matching algorithm is O((n - m + 1) * m), where `n` is the length of the text
 * and `m` is the length of the pattern. In the worst case, the algorithm compares the pattern to every substring of the
 * text.
 *
 * - Worst-case time complexity: O(n * m)
 * - Best-case time complexity: O(n), when the first character of the pattern matches the first character of the text
 *
 * # Example:
 * Consider the text `T = "ABABDABACDABABCABAB"` and the pattern `P = "ABAB"`.
 * The algorithm compares each substring of length 4 from the text to the pattern:
 * ```
 * "ABAB" matches at index 0
 * "ABAB" matches at index 10
 * "ABAB" matches at index 15
 * ```
 *
 * # Advantages:
 * * Simple and easy to understand: The algorithm is straightforward and does not require complex data structures.
 * * Suitable for small inputs: For small strings, the Naive String Matching algorithm can be efficient enough.
 *
 * # Disadvantages:
 * * Inefficient for large inputs: The algorithm performs a lot of redundant comparisons, leading to slower performance
 * for long texts or patterns.
 * * Not optimal for all cases: More advanced algorithms like Knuth-Morris-Pratt (KMP) or Boyer-Moore are more efficient
 * in most cases.

 * # Applications:
 * - Searching for a pattern in small strings or documents
 * - Basic text processing tasks where simplicity is preferred over speed
 *
 * @property info An optional string that provides additional information about the Naive String Matching algorithm
 * implementation or usage.
 * @constructor Creates a new NaiveStringMatching annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class NaiveStringMatching(val info: String = "")
