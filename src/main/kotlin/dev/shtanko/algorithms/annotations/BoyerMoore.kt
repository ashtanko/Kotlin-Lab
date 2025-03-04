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
 * The Boyer-Moore algorithm is an efficient string searching (or pattern matching) algorithm that finds occurrences
 * of a pattern within a text by skipping sections of the text using precomputed information about the pattern and the
 * text.
 * It is widely regarded as one of the most efficient algorithms for searching in large texts.
 *
 * # How It Works
 *
 * 1: Preprocessing the Pattern:
 * The Boyer-Moore algorithm preprocesses the pattern to build two key tables:
 * - The **bad character rule** table: Helps skip ahead in the text when a character mismatch occurs.
 * - The **good suffix rule** table: Helps skip ahead when a partial match is found.
 *
 * 2: Searching:
 * Starting from the end of the pattern, the algorithm compares characters in the pattern with characters in the text.
 * If there is a mismatch, the algorithm uses the precomputed tables to shift the pattern, skipping sections of the text
 * where matches cannot occur.
 *
 * 3: Repeat:
 * This process continues until a match is found or the pattern is completely shifted past the text.
 *
 * # Example:
 * For the pattern "ABAB" and the text "ABABABAB", the Boyer-Moore algorithm would skip over sections of the text
 * that have already been checked based on the precomputed tables, making the search more efficient than traditional
 * algorithms like brute force.
 *
 * # Advantages:
 * * Efficiency: Boyer-Moore is highly efficient, particularly for long texts, with a worst-case time complexity
 * of O(n + m), where n is the length of the text and m is the length of the pattern.
 * * Skipping: The algorithm can skip large portions of the text using the precomputed tables, making it faster than
 * brute force.
 *
 * # Common Applications:
 * - String searching in text editors and search engines
 * - DNA sequence matching
 * - Text processing and data mining
 *
 * @property info An optional string that provides additional information about the Boyer-Moore algorithm implementation
 * or usage.
 * @constructor Creates a new BoyerMoore annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class BoyerMoore(val info: String = "")
