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
 * The Hashing technique is a method used to efficiently store and retrieve data in data structures such as hash tables,
 * hash maps, and hash sets. It leverages a hash function to map keys to unique indices in a fixed-size array or table,
 * enabling fast data access with average-case constant time complexity, O(1).
 *
 * # How It Works
 *
 * 1: Hash Function:
 * A hash function is used to map a key to a specific index in the data structure. The function takes an input (or key)
 * and produces a hash code that determines the index where the corresponding value will be stored or retrieved.
 * A good hash function minimizes collisions and evenly distributes keys across the hash table.
 *
 * 2: Collision Handling:
 * Collisions occur when two or more keys produce the same hash code. There are several strategies to handle collisions:
 * - **Chaining**: Store multiple values at the same index using a list or linked list.
 * - **Open Addressing**: Probe for the next available index when a collision occurs, using methods like linear probing,
 * quadratic probing, or double hashing.
 *
 * 3: Insertion and Lookup:
 * When inserting a key-value pair, the hash function is applied to the key, and the value is stored at the resulting
 * index.
 * For lookup, the key is hashed again, and the value is retrieved from the corresponding index.
 *
 * # Example:
 * In a hash map where keys are strings and values are integers, the hash function maps each string key to an index in
 * the array. When searching for a value, the same hash function is applied to the key, allowing direct access to the
 * index where the value is stored, ensuring efficient retrieval.
 *
 * # Advantages:
 * * Fast Data Retrieval: Hashing allows for constant time complexity, O(1), for insertion and lookup operations on
 * average.
 * * Efficient Memory Usage: Hash tables and hash maps offer an efficient way to store key-value pairs, reducing the
 * need for linear search.
 *
 * # Disadvantages:
 * * Collisions: Hash collisions can degrade performance if not handled properly, leading to slower operations.
 * * Poor Hash Functions: A poorly designed hash function can lead to clustering and uneven distribution of keys,
 * increasing the likelihood of collisions.
 *
 * # Common Applications:
 * - Implementing hash maps, hash sets, and dictionaries
 * - Caching and memoization techniques
 * - Cryptography and data integrity (hash functions)
 *
 * @property info An optional string that provides additional information about the Hashing technique implementation
 * or usage.
 * @constructor Creates a new Hashing annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class Hashing(val info: String = "")
