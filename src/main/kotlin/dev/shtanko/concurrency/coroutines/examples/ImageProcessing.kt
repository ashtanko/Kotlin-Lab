package dev.shtanko.concurrency.coroutines.examples

import dev.shtanko.utils.ResourceFilePath
import dev.shtanko.utils.readImageBytes
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

private const val RED_WEIGHT = 0.299
private const val GREEN_WEIGHT = 0.587
private const val BLUE_WEIGHT = 0.114

private const val SEPIA_RED_SCALE_R = 0.393
private const val SEPIA_RED_SCALE_G = 0.769
private const val SEPIA_RED_SCALE_B = 0.189
private const val SEPIA_GREEN_SCALE_R = 0.349
private const val SEPIA_GREEN_SCALE_G = 0.686
private const val SEPIA_GREEN_SCALE_B = 0.168
private const val SEPIA_BLUE_SCALE_R = 0.272
private const val SEPIA_BLUE_SCALE_G = 0.534
private const val SEPIA_BLUE_SCALE_B = 0.131

private const val MAX_COLOR_VALUE = 255
private const val BRIGHTNESS_ADJUSTMENT = 40

private const val DEFAULT_THRESHOLD = 128
private const val RGB_COMPONENT_COUNT = 3
private const val MIN_COLOR = 0
private const val MAX_COLOR = 255

private const val KERNEL_SIZE = 3
private const val KERNEL_CENTER = KERNEL_SIZE / 2

private val GAUSSIAN_KERNEL = arrayOf(
    doubleArrayOf(1.0 / 16, 2.0 / 16, 1.0 / 16),
    doubleArrayOf(2.0 / 16, 4.0 / 16, 2.0 / 16),
    doubleArrayOf(1.0 / 16, 2.0 / 16, 1.0 / 16)
)

fun toGrayscale(image: BufferedImage): BufferedImage {
    val result = BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB)
    for (x in 0 until image.width) {
        for (y in 0 until image.height) {
            val rgb = Color(image.getRGB(x, y))
            val gray = (RED_WEIGHT * rgb.red + GREEN_WEIGHT * rgb.green + BLUE_WEIGHT * rgb.blue).toInt()
            val grayColor = Color(gray, gray, gray)
            result.setRGB(x, y, grayColor.rgb)
        }
    }
    return result
}

fun applySepia(image: BufferedImage): BufferedImage {
    for (x in 0 until image.width) {
        for (y in 0 until image.height) {
            val color = Color(image.getRGB(x, y))

            val r = color.red
            val g = color.green
            val b = color.blue

            val tr = (SEPIA_RED_SCALE_R * r + SEPIA_RED_SCALE_G * g + SEPIA_RED_SCALE_B * b).toInt()
                .coerceAtMost(MAX_COLOR_VALUE)
            val tg = (SEPIA_GREEN_SCALE_R * r + SEPIA_GREEN_SCALE_G * g + SEPIA_GREEN_SCALE_B * b).toInt()
                .coerceAtMost(MAX_COLOR_VALUE)
            val tb = (SEPIA_BLUE_SCALE_R * r + SEPIA_BLUE_SCALE_G * g + SEPIA_BLUE_SCALE_B * b).toInt()
                .coerceAtMost(MAX_COLOR_VALUE)

            val newColor = Color(tr, tg, tb)
            image.setRGB(x, y, newColor.rgb)
        }
    }
    return image
}

fun invertColors(image: BufferedImage): BufferedImage {
    val result = BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB)
    for (x in 0 until image.width) {
        for (y in 0 until image.height) {
            val rgb = Color(image.getRGB(x, y))
            val inverted = Color(
                MAX_COLOR_VALUE - rgb.red,
                MAX_COLOR_VALUE - rgb.green,
                MAX_COLOR_VALUE - rgb.blue,
            )
            result.setRGB(x, y, inverted.rgb)
        }
    }
    return result
}

fun adjustBrightness(image: BufferedImage, delta: Int = BRIGHTNESS_ADJUSTMENT): BufferedImage {
    for (x in 0 until image.width) {
        for (y in 0 until image.height) {
            val color = Color(image.getRGB(x, y))
            val r = (color.red + delta).coerceIn(0, MAX_COLOR_VALUE)
            val g = (color.green + delta).coerceIn(0, MAX_COLOR_VALUE)
            val b = (color.blue + delta).coerceIn(0, MAX_COLOR_VALUE)
            val newColor = Color(r, g, b)
            image.setRGB(x, y, newColor.rgb)
        }
    }
    return image
}

fun toBlackAndWhite(
    image: BufferedImage,
    threshold: Int = DEFAULT_THRESHOLD,
): BufferedImage {
    val result = BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB)
    for (x in 0 until image.width) {
        for (y in 0 until image.height) {
            val rgb = Color(image.getRGB(x, y))
            val averageColor = (rgb.red + rgb.green + rgb.blue) / RGB_COMPONENT_COUNT
            val bw = if (averageColor < threshold) MIN_COLOR else MAX_COLOR
            result.setRGB(x, y, Color(bw, bw, bw).rgb)
        }
    }
    return result
}

fun mirrorHorizontal(image: BufferedImage): BufferedImage {
    val result = BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB)
    for (x in 0 until image.width) {
        for (y in 0 until image.height) {
            result.setRGB(image.width - x - 1, y, image.getRGB(x, y))
        }
    }
    return result
}

fun flipVertical(image: BufferedImage): BufferedImage {
    val result = BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB)
    for (x in 0 until image.width) {
        for (y in 0 until image.height) {
            result.setRGB(x, image.height - y - 1, image.getRGB(x, y))
        }
    }
    return result
}

fun rotate90(image: BufferedImage): BufferedImage {
    val result = BufferedImage(image.height, image.width, BufferedImage.TYPE_INT_RGB)
    for (x in 0 until image.width) {
        for (y in 0 until image.height) {
            result.setRGB(y, image.width - x - 1, image.getRGB(x, y))
        }
    }
    return result
}

fun display(image: ByteArray) {
    val icon = ImageIcon(image)
    val label = JLabel(icon)
    val frame = JFrame("Processed Image")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.contentPane.add(label)
    frame.pack()
    frame.isVisible = true
}

fun decodeImage(bytes: ByteArray): BufferedImage =
    ImageIO.read(ByteArrayInputStream(bytes)) ?: error("Invalid image")

fun BufferedImage.toByteArray(format: String = "png"): ByteArray {
    val out = java.io.ByteArrayOutputStream()
    ImageIO.write(this, format, out)
    return out.toByteArray()
}

fun blur(image: BufferedImage, iterations: Int = 1): BufferedImage {
    var currentImage = image
    repeat(iterations) {
        currentImage = singleBlurPass(currentImage)
    }
    return currentImage
}

private fun singleBlurPass(image: BufferedImage): BufferedImage {
    val width = image.width
    val height = image.height
    val result = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

    for (x in 0 until width) {
        for (y in 0 until height) {
            var rSum = 0.0
            var gSum = 0.0
            var bSum = 0.0

            for (kx in 0 until KERNEL_SIZE) {
                for (ky in 0 until KERNEL_SIZE) {
                    val px = (x + kx - KERNEL_CENTER).coerceIn(0, width - 1)
                    val py = (y + ky - KERNEL_CENTER).coerceIn(0, height - 1)
                    val color = Color(image.getRGB(px, py))
                    val weight = GAUSSIAN_KERNEL[kx][ky]
                    rSum += color.red * weight
                    gSum += color.green * weight
                    bSum += color.blue * weight
                }
            }

            val r = rSum.toInt().coerceIn(0, MAX_COLOR_VALUE)
            val g = gSum.toInt().coerceIn(0, MAX_COLOR_VALUE)
            val b = bSum.toInt().coerceIn(0, MAX_COLOR_VALUE)
            result.setRGB(x, y, Color(r, g, b).rgb)
        }
    }
    return result
}

fun main(): Unit = runBlocking {
    val path: ResourceFilePath = "images/bd.jpg"
    val imageBytes = path.readImageBytes()

    flowOf(imageBytes)
        .map { decodeImage(it) }
        .map { toGrayscale(it) }
        .map { applySepia(it) }
        .map { adjustBrightness(it) }
        .map { mirrorHorizontal(it) }
        .map { rotate90(it) }
        .map { rotate90(it) }
        .map { rotate90(it) }
        .map { rotate90(it) }
        .map { flipVertical(it) }
        .map { flipVertical(it) }
        .map { toBlackAndWhite(it) }
        .map { invertColors(it) }
        .map { blur(it, iterations = 4) }
        .map { it.toByteArray() }
        .collect { display(it) }
}
