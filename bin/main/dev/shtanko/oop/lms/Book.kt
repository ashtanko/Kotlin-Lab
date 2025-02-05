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

package dev.shtanko.oop.lms

/**
 * Represents a book in the library management system.
 *
 * @property title The title of the book.
 * @property author The author of the book.
 * @property isbn The International Standard Book Number of the book.
 * @property genre The genre of the book.
 * @property isAvailable Indicates whether the book is currently available for borrowing.
 */
data class Book(
    val title: String,
    val author: String,
    val isbn: String,
    val genre: String,
    var isAvailable: Boolean = true,
)
