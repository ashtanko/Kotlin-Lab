@file:Suppress("MagicNumber")

package dev.shtanko.concurrency.coroutines.tasks

import kotlin.random.Random
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.yield

class QuickSortTask(
    private val arraySize: Int = 80_000,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : BaseTask<Long>(
    name = "Quick Sort",
    description = "Sorting $arraySize elements using quick sort",
    dispatcher = dispatcher,
) {
    private var swaps = 0L
    private var processedElements = 0

    override suspend fun execute(): Long {
        val array = IntArray(arraySize) { Random.nextInt() }
        swaps = 0L
        processedElements = 0

        quickSort(array, 0, arraySize - 1)
        return swaps
    }

    private suspend fun quickSort(arr: IntArray, low: Int, high: Int) {
        if (low < high) {
            val pi = partition(arr, low, high)

            processedElements += (high - low)
            if (processedElements % 1000 == 0) {
                yield()
                updateProgress(processedElements.toFloat() / (arraySize * 2))
            }

            quickSort(arr, low, pi - 1)
            quickSort(arr, pi + 1, high)
        }
    }

    private fun partition(arr: IntArray, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low - 1

        for (j in low until high) {
            if (arr[j] < pivot) {
                i++
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
                swaps++
            }
        }

        val temp = arr[i + 1]
        arr[i + 1] = arr[high]
        arr[high] = temp
        swaps++

        return i + 1
    }
}
