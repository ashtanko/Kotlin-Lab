package dev.shtanko.algorithms.sorts

/**
 * Invented in 1945 by John von Neumann, merge sort is an efficient algorithm using the divide and conquer approach
 * which is to divide a big problem into smaller problems and solve them. Conceptually, a merge sort works as follows:
 * 1) Divide the unsorted list into n sublists, each containing 1 element (a list of 1 element is considered sorted).
 * 2) Repeatedly merge sublists to produce new sorted sublists until there is only 1 sublist remaining.
 *
 * Worst-case performance       O(n log n)
 * Best-case performance        O(n log n)
 * Average performance          O(n log n)
 * Worst-case space complexity  O(n)
 */
class MergeSort : AbstractSortStrategy {
    override fun <T : Comparable<T>> perform(arr: Array<T>) {
        val aux = arr.clone()
        sort(arr, aux, 0, arr.size - 1)
    }

    private fun <T : Comparable<T>> sort(arr: Array<T>, aux: Array<T>, low: Int, high: Int) {
        if (high <= low) return
        val mid = (low + high) / 2
        sort(arr, aux, low, mid)
        sort(arr, aux, mid + 1, high)
        merge(arr, aux, low, mid, high)
    }

    private fun <T : Comparable<T>> merge(arr: Array<T>, aux: Array<T>, low: Int, mid: Int, high: Int) {
        System.arraycopy(arr, low, aux, low, high - low + 1)

        var i = low
        var j = mid + 1

        for (k in low..high) {
            when {
                i > mid -> arr[k] = aux[j++]
                j > high -> arr[k] = aux[i++]
                aux[j] < aux[i] -> arr[k] = aux[j++]
                else -> arr[k] = aux[i++]
            }
        }
    }
}
