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
 * The Knuth-Morris-Pratt (KMP) algorithm is an efficient string matching algorithm used to search for occurrences of
 * a pattern within a text. It improves upon the brute-force approach by avoiding unnecessary re-examination of
 * characters in the text, leveraging previously gathered information to skip sections of the text that have already
 * been matched.
 *
 * # How It Works
 *
 * 1: Preprocessing:
 * The algorithm first builds a partial match table (also called the "prefix function" or "failure function") for
 * the pattern.
 * This table stores the length of the longest proper prefix of the pattern that is also a suffix, for each prefix
 * of the pattern.
 * This allows the search to skip over parts of the text where a mismatch has already occurred.
 *
 * 2: Searching:
 * Once the preprocessing step is complete, the KMP algorithm proceeds to search the text for the pattern. During the
 * search, if a mismatch occurs, the algorithm uses the information in the partial match table to shift the pattern
 * without having to re-check already matched characters.
 *
 * 3: Efficiency:
 * The KMP algorithm has a time complexity of O(n + m), where n is the length of the text and m is the length of the
 * pattern, making it more efficient than the naive string matching algorithm, which has a worst-case time complexity
 * of O(n * m).
 *
 * # Example:
 * Consider the text "ABABDABACDABABCABAB" and the pattern "ABABCABAB". The algorithm will preprocess the pattern into a
 * partial match table and use this information to efficiently search for matches within the text.
 *
 * # Advantages:
 * * Improved Time Complexity: The KMP algorithm improves performance over brute-force methods by avoiding redundant
 * comparisons.
 * * Efficient for Large Texts: The algorithm is well-suited for searching within large strings or texts, as it ensures
 * linear time complexity.
 *
 * # Disadvantages:
 * * Preprocessing Overhead: While KMP's search is fast, the preprocessing step takes O(m) time and space, which may
 * not be beneficial
 *   for very short patterns or texts.
 * * Complexity: Understanding and implementing the KMP algorithm can be more difficult than simpler brute-force
 * approaches, particularly when handling the preprocessing step.
 *
 * # Common Applications:
 * - String matching (e.g., searching for substrings in text)
 * - Pattern recognition in computational biology (e.g., DNA sequence matching)
 * - Text editing and searching tools (e.g., find and replace in text editors)
 * - Data mining and search engines (e.g., pattern searching in large datasets)
 *
 * @property info An optional string that provides additional information about the Knuth-Morris-Pratt algorithm
 * implementation or usage.
 * @constructor Creates a new KnuthMorrisPratt annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class KnuthMorrisPratt(val info: String = "")
