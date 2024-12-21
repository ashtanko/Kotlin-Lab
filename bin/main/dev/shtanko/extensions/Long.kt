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

package dev.shtanko.extensions

import dev.shtanko.algorithms.DECIMAL

/**
 * Check self is a super palindrome
 */
fun Long.isSuperPalindrome(): Boolean {
    var x = this
    var isSuper = false
    for (i in 0 until 2) {
        isSuper = x.isPalindrome()
        x /= 2
        if (isSuper.not()) return false
    }
    return isSuper
}

/**
 * Check self is a palindrome
 */
fun Long.isPalindrome(): Boolean {
    return this == this.reverse()
}

/**
 * Reverse self
 */
fun Long.reverse(): Long {
    var ans = 0L
    var x = this
    while (x > 0) {
        ans = DECIMAL * ans + x % DECIMAL
        x /= DECIMAL
    }
    return ans
}
