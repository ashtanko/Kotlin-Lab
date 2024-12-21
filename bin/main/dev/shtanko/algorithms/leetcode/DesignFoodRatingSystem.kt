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

package dev.shtanko.algorithms.leetcode

import java.util.PriorityQueue
import java.util.TreeSet

/**
 * 2353. Design a Food Rating System
 * @see <a href="https://leetcode.com/problems/design-a-food-rating-system">Source</a>
 */
interface FoodRatings {
    fun changeRating(food: String, newRating: Int)

    fun highestRated(cuisine: String): String
}

class FoodRatingsPriorityQueue(foods: Array<String>, cuisines: Array<String>, ratings: IntArray) : FoodRatings {

    // Map food with its rating.
    private var foodRatingMap: MutableMap<String, Int> = HashMap()

    // Map food with the cuisine it belongs to.
    private var foodCuisineMap: MutableMap<String, String> = HashMap()

    // Store all food of a cuisine in a priority queue (to sort them on ratings/name)
    // Priority queue element -> Food: (foodRating, foodName)
    private var cuisineFoodMap: MutableMap<String, PriorityQueue<Food>> = HashMap()

    init {
        for (i in foods.indices) {
            // Store 'rating' and 'cuisine' of the current 'food' in 'foodRatingMap' and 'foodCuisineMap' maps.
            foodRatingMap[foods[i]] = ratings[i]
            foodCuisineMap[foods[i]] = cuisines[i]
            // Insert the '(rating, name)' element into the current cuisine's priority queue.
            cuisineFoodMap.computeIfAbsent(
                cuisines[i],
            ) { PriorityQueue() }.add(
                Food(
                    ratings[i],
                    foods[i],
                ),
            )
        }
    }

    override fun changeRating(food: String, newRating: Int) {
        // Update food's rating in the 'foodRating' map.
        foodRatingMap[food] = newRating
        // Insert the '(new food rating, food name)' element into the respective cuisine's priority queue.
        val cuisineName = foodCuisineMap[food]
        cuisineFoodMap[cuisineName]?.add(Food(newRating, food))
    }

    override fun highestRated(cuisine: String): String {
        // Get the highest rated 'food' of 'cuisine'.
        var highestRated: Food = cuisineFoodMap[cuisine]?.peek() ?: return ""

        // If the latest rating of 'food' doesn't match with the 'rating' on which it was sorted in the priority queue,
        // then we discard this element from the priority queue.
        while (foodRatingMap[highestRated.foodName] != highestRated.foodRating) {
            cuisineFoodMap[cuisine]?.poll()
            highestRated = cuisineFoodMap[cuisine]?.peek() ?: return ""
        }

        // Return the name of the highest-rated 'food' of 'cuisine'.
        return highestRated.foodName
    }

    internal class Food(
        var foodRating: Int, // store the food's name.
        var foodName: String,
    ) : Comparable<Food> {
        // Implement the compareTo method for comparison
        override fun compareTo(other: Food): Int {
            // If food ratings are the same, sort based on their names
            // (lexicographically smaller name food will be on top)
            if (foodRating == other.foodRating) {
                return foodName.compareTo(other.foodName)
            }
            // Sort based on food rating (bigger rating food will be on top)
            return -1 * foodRating.compareTo(other.foodRating)
        }
    }
}

class FoodRatingsSortedSet(foods: Array<String>, cuisines: Array<String>, ratings: IntArray) : FoodRatings {

    // Map food with its rating.
    private val foodRatingMap: MutableMap<String, Int> = HashMap()

    // Map food with cuisine it belongs to.
    private val foodCuisineMap: MutableMap<String, String> = HashMap()

    // Store all food of a cuisine in set (to sort them on ratings/name)
    // Set element -> Pair: (-1 * foodRating, foodName)
    private val cuisineFoodMap: MutableMap<String, TreeSet<Pair<Int, String>>> = HashMap()

    init {
        foodRatings(foods, cuisines, ratings)
    }

    override fun changeRating(food: String, newRating: Int) {
        // Fetch cuisine name for food.
        val cuisineName = foodCuisineMap[food]

        // Find and delete the element from the respective cuisine's set.
        val cuisineSet = cuisineFoodMap.getOrDefault(cuisineName, TreeSet())
        val oldElement: Pair<Int, String> = Pair(-foodRatingMap.getOrDefault(food, 0), food)
        cuisineSet.remove(oldElement)

        // Update food's rating in 'foodRating' map.
        foodRatingMap[food] = newRating
        // Insert the '(-1 * new rating, name)' element in the respective cuisine's set.
        cuisineSet.add(Pair(-newRating, food))
    }

    override fun highestRated(cuisine: String): String {
        val highestRated = cuisineFoodMap.getOrDefault(cuisine, TreeSet()).first()
        // Return name of the highest rated 'food' of 'cuisine'.
        return highestRated.second
    }

    private fun foodRatings(foods: Array<String>, cuisines: Array<String>, ratings: IntArray) {
        for (i in foods.indices) {
            // Store 'rating' and 'cuisine' of current 'food' in 'foodRatingMap' and 'foodCuisineMap' maps.
            foodRatingMap[foods[i]] = ratings[i]
            foodCuisineMap[foods[i]] = cuisines[i]

            // Insert the '(-1 * rating, name)' element in the current cuisine's set.
            cuisineFoodMap
                .computeIfAbsent(
                    cuisines[i],
                ) {
                    TreeSet<Pair<Int, String>>(
                        java.util.Comparator { a: Pair<Int, String>, b: Pair<Int, String> ->
                            val compareByRating = a.first.compareTo(b.first)
                            if (compareByRating == 0) {
                                // If ratings are equal, compare by food name (in ascending order).
                                return@Comparator a.second.compareTo(b.second)
                            }
                            compareByRating
                        },
                    )
                }
                .add(Pair(-ratings[i], foods[i]))
        }
    }
}
