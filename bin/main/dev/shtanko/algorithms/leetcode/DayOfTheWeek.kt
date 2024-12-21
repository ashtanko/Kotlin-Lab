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

private const val MONDAY = 1
private const val TUESDAY = 2
private const val THURSDAY = 4
private const val FRIDAY = 5
private const val DAYS_IN_WEEK = 7
private const val JANUARY = 13
private const val MARCH = 3
private const val APRIL = 4
private const val MONTHS_IN_YEAR = 12
private const val ONE_MONTH = 1
private const val ONE_YEAR = 1
private const val YEAR_OF_THE_CENTURY = 100

fun Triple<Int, Int, Int>.dayOfTheWeek(): String {
    val days = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
    val day = first
    var month = second
    var year = third

    if (month < MARCH) {
        month += MONTHS_IN_YEAR
        year -= ONE_YEAR
    }
    val c = year / YEAR_OF_THE_CENTURY
    year %= YEAR_OF_THE_CENTURY
    val increasedMonth = month + ONE_MONTH
    val w = c / THURSDAY - TUESDAY * c + year + year / APRIL + JANUARY * increasedMonth / FRIDAY + day - MONDAY
    val week = w % DAYS_IN_WEEK
    val increasedWeek = week + DAYS_IN_WEEK
    return days[increasedWeek % DAYS_IN_WEEK]
}
