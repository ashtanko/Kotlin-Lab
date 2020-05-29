package dev.shtanko.algorithms.sorts

import dev.shtanko.algorithms.extensions.swap

/**
 * Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 * Each iteration, insertion sort removes one element from the input data, finds the location it belongs within
 * the sorted list, and inserts it there. It repeats until no input elements remain.
 */
class InsertionSort : AbstractSortStrategy {
    override fun <T : Comparable<T>> perform(arr: Array<T>) {
        for (i in 1 until arr.size) {
            for (j in i downTo 1) {
                if (arr[j - 1] < arr[j]) break
                arr.swap(j, j - 1)
            }
        }
    }
}
