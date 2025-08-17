package dev.shtanko.concurrency.coroutines.examples

import dev.shtanko.utils.ResourceFilePath
import dev.shtanko.utils.readImageBytes
import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.sqrt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

private const val KERNEL_RADIUS = 1

private val KERNEL_X = arrayOf(
    intArrayOf(-1, 0, 1),
    intArrayOf(-2, 0, 2),
    intArrayOf(-1, 0, 1),
)

private val KERNEL_Y = arrayOf(
    intArrayOf(-1, -2, -1),
    intArrayOf(0, 0, 0),
    intArrayOf(1, 2, 1),
)

suspend fun processImageParallel(image: BufferedImage): BufferedImage = coroutineScope {
    val segments = segmentImage(image, numberOfSegments = 8)

    val processedRegions = segments.map { segment ->
        async(Dispatchers.Default) {
            val edges = detectEdges(segment)
            val filtered = applyRegionSpecificFilter(segment)
            blendSegment(filtered, edges)
        }
    }

    val finalImage = mergeSegments(processedRegions.awaitAll(), image.width, image.height)
    return@coroutineScope finalImage
}

fun segmentImage(image: BufferedImage, numberOfSegments: Int): List<BufferedImage> {
    val heightPerSegment = image.height / numberOfSegments
    return List(numberOfSegments) { i ->
        val y = i * heightPerSegment
        val h = if (i == numberOfSegments - 1) image.height - y else heightPerSegment
        image.getSubimage(0, y, image.width, h)
    }
}

fun detectEdges(image: BufferedImage): BufferedImage {
    val w = image.width
    val h = image.height
    val edgeImage = BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)

    for (y in KERNEL_RADIUS until h - KERNEL_RADIUS) {
        for (x in KERNEL_RADIUS until w - KERNEL_RADIUS) {
            var gx = 0
            var gy = 0

            for (ky in -KERNEL_RADIUS..KERNEL_RADIUS) {
                for (kx in -KERNEL_RADIUS..KERNEL_RADIUS) {
                    val pixel = Color(image.getRGB(x + kx, y + ky)).red
                    gx += pixel * KERNEL_X[ky + KERNEL_RADIUS][kx + KERNEL_RADIUS]
                    gy += pixel * KERNEL_Y[ky + KERNEL_RADIUS][kx + KERNEL_RADIUS]
                }
            }

            val magnitude = sqrt((gx * gx + gy * gy).toDouble())
                .toInt()
                .coerceIn(0, MAX_COLOR_VALUE)
            val gray = Color(magnitude, magnitude, magnitude)
            edgeImage.setRGB(x, y, gray.rgb)
        }
    }

    return edgeImage
}

fun applyRegionSpecificFilter(image: BufferedImage): BufferedImage {
    val output = BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB)
    for (y in 0 until image.height) {
        for (x in 0 until image.width) {
            val c = Color(image.getRGB(x, y))
            val r = MAX_COLOR - c.red
            val g = MAX_COLOR - c.green
            val b = MAX_COLOR - c.blue
            output.setRGB(x, y, Color(r, g, b).rgb)
        }
    }
    return output
}

fun blendSegment(filtered: BufferedImage, edges: BufferedImage): BufferedImage {
    val w = filtered.width
    val h = filtered.height
    val blended = BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)

    for (y in 0 until h) {
        for (x in 0 until w) {
            val c1 = Color(filtered.getRGB(x, y))
            val c2 = Color(edges.getRGB(x, y))

            val r = ((c1.red + c2.red) / 2).coerceIn(0, MAX_COLOR)
            val g = ((c1.green + c2.green) / 2).coerceIn(0, MAX_COLOR)
            val b = ((c1.blue + c2.blue) / 2).coerceIn(0, MAX_COLOR)

            blended.setRGB(x, y, Color(r, g, b).rgb)
        }
    }

    return blended
}

fun mergeSegments(segments: List<BufferedImage>, totalWidth: Int, totalHeight: Int): BufferedImage {
    val result = BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_RGB)
    var currentY = 0
    for (segment in segments) {
        result.graphics.drawImage(segment, 0, currentY, null)
        currentY += segment.height
    }
    return result
}

fun main() = runBlocking {
    val path: ResourceFilePath = "images/bd.jpg"
    val inputImage = decodeImage(path.readImageBytes())
    val result = processImageParallel(inputImage)

    val resultBytes = result.toByteArray()
    display(resultBytes)
}
