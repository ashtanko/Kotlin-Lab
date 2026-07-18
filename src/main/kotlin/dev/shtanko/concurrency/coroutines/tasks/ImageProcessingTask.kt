@file:Suppress("MagicNumber")

package dev.shtanko.concurrency.coroutines.tasks

import kotlin.random.Random
import kotlinx.coroutines.yield

class ImageProcessingTask(
    private val width: Int = 800,
    private val height: Int = 600,
) : BaseTask<String>(
    name = "Image Processing",
    description = "Applying filters to ${width}x$height image",
) {
    override suspend fun execute(): String {
        val pixels = Array(height) { IntArray(width) { Random.nextInt(256) } }
        val processed = Array(height) { IntArray(width) }

        // Apply Gaussian blur
        val kernel = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(2, 4, 2),
            intArrayOf(1, 2, 1),
        )
        val kernelSum = 16

        for (y in 1 until height - 1) {
            if (y % 20 == 0) {
                yield()
                updateProgress(y.toFloat() / height)
            }

            for (x in 1 until width - 1) {
                var sum = 0
                for (ky in -1..1) {
                    for (kx in -1..1) {
                        sum += pixels[y + ky][x + kx] * kernel[ky + 1][kx + 1]
                    }
                }
                processed[y][x] = sum / kernelSum
            }
        }

        // Calculate average brightness
        val avgBrightness = processed.map { it.toList() }.flatten().average()
        return "Avg brightness: %.2f".format(avgBrightness)
    }
}
