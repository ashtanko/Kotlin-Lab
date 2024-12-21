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

package dev.shtanko.algorithms.hackerrank

import dev.shtanko.algorithms.annotations.level.Easy

/**
 * Time Conversion
 * @see <a href="https://www.hackerrank.com/challenges/time-conversion/problem">Source</a>
 */
@Easy
@Suppress("MagicNumber")
sealed interface TimeConversion {
    operator fun invoke(time: String): String

    data object BruteForce : TimeConversion {
        override fun invoke(time: String): String {
            val isAM = time.uppercase().contains("AM")

            // Split the time string into hours, minutes, and seconds
            val timeArray = time.split(":")
            val hours = timeArray[0]
            val minutes = timeArray[1]
            val seconds = timeArray[2].substring(0, 2) // Extract seconds without AM/PM

            // Convert to 24-hour format (military time)
            val militaryTime = when {
                hours == "12" && isAM -> "00:$minutes:$seconds"
                hours == "12" -> "$hours:$minutes:$seconds"
                !isAM -> "${(hours.toInt() + 12)}:$minutes:$seconds"
                else -> "$hours:$minutes:$seconds"
            }

            // Remove the AM/PM suffix
            return militaryTime
        }
    }
}
