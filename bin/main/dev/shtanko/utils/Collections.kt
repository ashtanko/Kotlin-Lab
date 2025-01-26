/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.utils

/**
 * Returns second element.
 * @throws [NoSuchElementException] if the list is empty.
 */
fun <T> List<T>.second(): T {
    if (isEmpty()) {
        throw NoSuchElementException("List is empty.")
    } else if (size == 1) {
        throw NoSuchElementException("List has only one element.")
    }
    return this[1]
}

/**
 * Returns third element.
 * @throws [NoSuchElementException] if the list is empty.
 */
fun <T> List<T>.third(): T {
    if (isEmpty()) {
        throw NoSuchElementException("List is empty.")
    } else if (size < 3) {
        throw NoSuchElementException("List has not enough elements.")
    }
    return this[2]
}

/**
 * Returns fourth element.
 * @throws [NoSuchElementException] if the list is empty.
 */
fun <T> List<T>.fourth(): T {
    if (isEmpty()) {
        throw NoSuchElementException("List is empty.")
    }
    if (size < 4) {
        throw NoSuchElementException("List has not enough elements.")
    }
    return this[3]
}
