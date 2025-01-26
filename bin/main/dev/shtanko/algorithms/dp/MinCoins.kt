/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.algorithms.dp

/**
 * Coin Change Problem
 * Given a set of coins and a target amount, find the minimum number of coins needed to make up that amount.
 *
 * Find the minimum number of coins needed to make up a given amount using dynamic programming.
 *
 * @param coins An array of coin denominations.
 * @param amount The target amount to make up.
 * @return The minimum number of coins needed to make up the amount. Returns -1 if it's not possible.
 */
fun minCoins(coins: IntArray, amount: Int): Int {
    // Create an array to store the minimum number of coins needed for each amount from 0 to the given amount.
    val dp = IntArray(amount + 1) { Int.MAX_VALUE }

    // Base case: No coins needed to make up amount 0.
    dp[0] = 0

    // Loop through all amounts from 1 to the target amount.
    for (i in 1..amount) {
        // Loop through each coin denomination.
        for (coin in coins) {
            // Check if the current coin can be used to make up the current amount (i) and if
            // using this coin leads to a smaller number of coins compared to the previous result.
            if (coin <= i && dp[i - coin] != Int.MAX_VALUE) {
                // Update the minimum number of coins needed for the current amount (i).
                dp[i] = minOf(dp[i], dp[i - coin] + 1)
            }
        }
    }

    // If dp[amount] is still Int.MAX_VALUE, it means it's not possible to make up the amount with the given coins.
    // Return -1 in such cases, otherwise, return the minimum number of coins needed.
    return if (dp[amount] == Int.MAX_VALUE) -1 else dp[amount]
}
