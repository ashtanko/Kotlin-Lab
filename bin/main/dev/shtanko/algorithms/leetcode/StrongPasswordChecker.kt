/*
 * Copyright 2020 Oleksii Shtanko
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

private const val START_VALUE = 1
private const val STRONG_PASSWORD_VALUE = 6
private const val OVER_LENGTH = 20

/**
 * Enum class representing the types of characters that can be present in a password.
 */
enum class CharacterType {
    LOWER_CASE, UPPER_CASE, DIGIT
}

/**
 * This function checks if a given password string is strong or not.
 * A strong password is defined as one that has at least 6 characters, and contains at least one lowercase letter,
 * one uppercase letter, and one digit.
 * The function returns the minimum number of steps required to make the password strong.
 * Each insertion, deletion, or replacement of any character in the password counts as one step.
 *
 * @param s The password string to check.
 * @return The minimum number of steps required to make the password strong.
 */
fun strongPasswordChecker(s: String): Int {
    var res = 0
    var lowerCases = START_VALUE
    var upperCases = START_VALUE
    var digits = START_VALUE
    val carr = s.toCharArray()
    val arr = IntArray(carr.size)

    calculateCharacterCounts(arr, carr) { characterType ->
        when (characterType) {
            CharacterType.LOWER_CASE -> lowerCases = 0
            CharacterType.UPPER_CASE -> upperCases = 0
            CharacterType.DIGIT -> digits = 0
        }
    }

    val totalMissing = lowerCases + upperCases + digits

    if (arr.size < STRONG_PASSWORD_VALUE) {
        res += calculateMissingCharacters(arr.size, totalMissing)
    } else {
        val local = arr.size - OVER_LENGTH
        val overLen = local.coerceAtLeast(0)
        res += handleExcessLength(arr, overLen)
        val leftOver = calculateLeftOver(arr)
        res += totalMissing.coerceAtLeast(leftOver)
    }

    return res
}

/**
 * This function calculates the counts of each type of character in the password string.
 *
 * @param arr An array to store the counts of each type of character.
 * @param carr The password string converted to a character array.
 * @param action A lambda function to update the counts of each type of character.
 */
private fun calculateCharacterCounts(
    arr: IntArray,
    carr: CharArray,
    action: (type: CharacterType) -> Unit,
) {
    var i = 0
    while (i < arr.size) {
        if (Character.isLowerCase(carr[i])) action.invoke(CharacterType.LOWER_CASE)
        if (Character.isUpperCase(carr[i])) action.invoke(CharacterType.UPPER_CASE)
        if (Character.isDigit(carr[i])) action.invoke(CharacterType.DIGIT)

        val j = i
        while (i < carr.size && carr[i] == carr[j]) i++
        arr[j] = i - j
    }
}

/**
 * This function calculates the number of missing characters needed to make the password strong.
 *
 * @param size The current size of the password.
 * @param totalMissing The total number of missing character types.
 * @return The number of missing characters needed.
 */
private fun calculateMissingCharacters(size: Int, totalMissing: Int): Int {
    return totalMissing + 0.coerceAtLeast(STRONG_PASSWORD_VALUE - (size + totalMissing))
}

/**
 * This function handles the case where the password is longer than the maximum allowed length.
 *
 * @param arr An array storing the counts of each type of character.
 * @param overLen The number of characters exceeding the maximum allowed length.
 * @return The number of steps required to reduce the password length to the maximum allowed length.
 */
private fun handleExcessLength(arr: IntArray, overLen: Int): Int {
    var res = overLen
    for (k in 1..2) {
        var i = 0
        while (i < arr.size && overLen > 0) {
            if (arr[i] < 3 || arr[i] % 3 != k - 1) {
                i++
                continue
            }
            arr[i] -= overLen.coerceAtMost(k)
            res -= k
            i++
        }
    }
    return res
}

/**
 * This function calculates the number of leftover characters after handling the excess length.
 *
 * @param arr An array storing the counts of each type of character.
 * @return The number of leftover characters.
 */
private fun calculateLeftOver(arr: IntArray): Int {
    var leftOver = 0
    var overLen = 0

    for (k in arr.indices) {
        if (arr[k] >= 3 && overLen > 0) {
            val need = arr[k] - 2
            arr[k] -= overLen
            overLen -= need
        }

        if (arr[k] >= 3) leftOver += arr[k] / 3
    }

    return leftOver
}
