package dev.shtanko.concurrency.coroutines.tasks

import kotlin.random.Random
import kotlinx.coroutines.yield

class SortingTask(
    private val arraySize: Int = 50_000,
) : BaseTask<Int>(
    name = "Array Sorting",
    description = "Sorting $arraySize elements",
) {
    override suspend fun execute(): Int {
        val array = IntArray(arraySize) { Random.nextInt() }
        var swaps = 0

        // Bubble sort for demonstration (intentionally inefficient for CPU load)
        for (i in 0 until arraySize - 1) {
            if (i % 100 == 0) {
                yield()
                updateProgress(i.toFloat() / arraySize)
            }

            for (j in 0 until arraySize - i - 1) {
                if (array[j] > array[j + 1]) {
                    val temp = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = temp
                    swaps++
                }
            }
        }

        return swaps
    }
}
