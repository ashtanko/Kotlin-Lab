/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.algorithms.annotations

/**
 * The Rabin-Karp algorithm is a string-searching algorithm that uses hashing to find an exact match of a pattern within
 * a text.
 * It is particularly useful when multiple pattern matching operations need to be performed efficiently.
 *
 * # How It Works
 *
 * 1. **Hash the Pattern:** Compute a hash value for the pattern.
 * 2. **Sliding Window:** Slide a window of the same length as the pattern across the text, computing the hash value of
 * the current window.
 * 3. **Hash Comparison:** Compare the hash value of the current window to the hash value of the pattern.
 * 4. **Exact Match Check:** If the hash values match, check for an actual match by comparing the characters directly
 * to avoid hash collisions.
 * 5. **Repeat:** Continue sliding the window across the text, checking for matches.
 *
 * # Time Complexity
 * - **Preprocessing Time:** O(m) where m is the length of the pattern.
 * - **Searching Time:** O(n) where n is the length of the text, for each comparison of hashes (ignoring hash
 * collisions).
 * - If a hash collision occurs, the algorithm performs a direct comparison which takes O(m) time.
 * - **Overall Complexity:** In the best case, O(n + m), but in the worst case (when hash collisions occur), the
 * complexity is O(n * m).
 *
 * # Example:
 * Consider the text "ababcababcab" and the pattern "abc".
 * - Compute the hash for the pattern "abc".
 * - Slide a window of size 3 (the length of the pattern) over the text and compute hashes for each window: "aba",
 * "bab", "abc", etc.
 * - When the hash for "abc" matches the pattern's hash, check for an exact match.
 *
 * # Advantages:
 * * Efficient for multiple pattern matching: The Rabin-Karp algorithm is ideal when searching for multiple patterns
 * in the same text.
 * * Average-case performance is linear: The average time complexity is O(n) under the assumption that the hash function
 * distributes values well and avoids collisions.
 *
 * # Disadvantages:
 * * Hash collisions: If many collisions occur, the algorithm may degrade to a slower brute-force approach.
 * * Preprocessing overhead: Computing the hash for each window can add overhead, especially for short patterns and
 * texts.
 *
 * # Applications:
 * - String matching problems where multiple patterns need to be searched efficiently in large text bodies.
 * - Plagiarism detection in documents or source code.
 * - Finding substrings with specific properties within larger data sets.
 *
 * @property info An optional string that provides additional information about the Rabin-Karp algorithm implementation
 * or usage.
 * @constructor Creates a new RabinKarp annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class RabinKarp(val info: String = "")
