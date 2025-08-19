@file:Suppress("MagicNumber")

package dev.shtanko.concurrency.coroutines.tasks

import kotlin.random.Random
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.yield

class MergeSortTask(
    private val arraySize: Int = 100_000,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : BaseTask<Long>(
    name = "Merge Sort",
    description = "Sorting $arraySize elements using merge sort",
    dispatcher = dispatcher,
) {
    private var comparisons = 0L
    private var totalOperations = 0
    private var currentOperation = 0

    override suspend fun execute(): Long {
        val array = IntArray(arraySize) { Random.nextInt() }
        comparisons = 0L
        totalOperations = (arraySize * kotlin.math.log2(arraySize.toDouble())).toInt()
        currentOperation = 0

        mergeSort(array, 0, arraySize - 1)
        return comparisons
    }

    private suspend fun mergeSort(arr: IntArray, left: Int, right: Int) {
        if (left < right) {
            val mid = left + (right - left) / 2

            mergeSort(arr, left, mid)
            mergeSort(arr, mid + 1, right)
            merge(arr, left, mid, right)

            currentOperation += (right - left)
            if (currentOperation % 1000 == 0) {
                yield()
                updateProgress(currentOperation.toFloat() / totalOperations)
            }
        }
    }

    private fun merge(arr: IntArray, left: Int, mid: Int, right: Int) {
        val leftArray = arr.sliceArray(left..mid)
        val rightArray = arr.sliceArray(mid + 1..right)

        var i = 0
        var j = 0
        var k = left

        while (i < leftArray.size && j < rightArray.size) {
            comparisons++
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i]
                i++
            } else {
                arr[k] = rightArray[j]
                j++
            }
            k++
        }

        while (i < leftArray.size) {
            arr[k] = leftArray[i]
            i++
            k++
        }

        while (j < rightArray.size) {
            arr[k] = rightArray[j]
            j++
            k++
        }
    }
}
