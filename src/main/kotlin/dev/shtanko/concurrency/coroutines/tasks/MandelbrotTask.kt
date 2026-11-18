@file:Suppress("MagicNumber")

package dev.shtanko.concurrency.coroutines.tasks

import kotlinx.coroutines.yield

class MandelbrotTask(
    private val width: Int = 400,
    private val height: Int = 300,
    private val maxIterations: Int = 256,
) : BaseTask<Int>(
    name = "Mandelbrot Set",
    description = "Calculating ${width}x$height fractal",
) {
    override suspend fun execute(): Int {
        var totalIterations = 0
        val totalPixels = width * height
        var processedPixels = 0

        for (y in 0 until height) {
            if (y % 10 == 0) yield()

            for (x in 0 until width) {
                val cx = (x - width / 2.0) * 4.0 / width
                val cy = (y - height / 2.0) * 4.0 / height

                totalIterations += calculateMandelbrot(cx, cy)
                processedPixels++

                if (processedPixels % 1000 == 0) {
                    updateProgress(processedPixels.toFloat() / totalPixels)
                }
            }
        }

        return totalIterations
    }

    private fun calculateMandelbrot(cx: Double, cy: Double): Int {
        var zx = 0.0
        var zy = 0.0
        var iteration = 0

        while (zx * zx + zy * zy < 4.0 && iteration < maxIterations) {
            val tmp = zx * zx - zy * zy + cx
            zy = 2.0 * zx * zy + cy
            zx = tmp
            iteration++
        }

        return iteration
    }
}
