package dev.shtanko.algorithms.search

/**
 * Binary search is an algorithm which finds the position of a target value within an array (Sorted)
 *
 * Worst-case performance       O(log(n))
 * Best-case performance        O(1)
 * Average performance          O(log(n))
 * Worst-case space complexity  O(1)
 */
class BinaryRecursiveSearch<T : Comparable<T>> : AbstractSearchStrategy<T> {
    override fun perform(arr: Array<T>, element: T): Int {
        return arr.search(0, arr.size - 1, element)
    }

    private fun Array<T>.search(low: Int, high: Int, element: T): Int {
        if (high >= low) {
            val mid = low.plus(high).div(2)

            if (this[mid] == element) {
                return mid
            }

            if (this[mid] > element) {
                return this.search(low, mid - 1, element)
            }
            return search(mid + 1, high, element)
        }
        return -1
    }
}
