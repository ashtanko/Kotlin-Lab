package dev.shtanko.concurrency.coroutines.examples

import java.awt.Color
import java.awt.image.BufferedImage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

private const val MAX_COLOR = 255

class AdvancedImageProcessingTest {
    private fun createSolidColorImage(width: Int, height: Int, color: Color): BufferedImage {
        return BufferedImage(width, height, BufferedImage.TYPE_INT_RGB).apply {
            for (x in 0 until width) {
                for (y in 0 until height) {
                    setRGB(x, y, color.rgb)
                }
            }
        }
    }

    private fun imagesAreEqual(img1: BufferedImage, img2: BufferedImage): Boolean {
        if (img1.width != img2.width || img1.height != img2.height) return false
        for (x in 0 until img1.width) {
            for (y in 0 until img1.height) {
                if (img1.getRGB(x, y) != img2.getRGB(x, y)) return false
            }
        }
        return true
    }

    @Test
    fun `applyRegionSpecificFilter inverts colors imagesAreEqual`() {
        val original = createSolidColorImage(5, 5, Color(50, 100, 150))
        val expected = createSolidColorImage(5, 5, Color(205, 155, 105)) // 255 - original colors
        val inverted = applyRegionSpecificFilter(original)

        assertTrue(imagesAreEqual(expected, inverted))
    }

    @Test
    fun `detectEdges returns black image for uniform color`() {
        val image = createSolidColorImage(10, 10, Color(100, 100, 100))
        val edges = detectEdges(image)

        for (x in 1 until edges.width - 1) {
            for (y in 1 until edges.height - 1) {
                val rgb = Color(edges.getRGB(x, y))
                assertTrue(rgb.red < 10, "Expected low edge intensity, got ${rgb.red}")
                assertEquals(rgb.red, rgb.green)
                assertEquals(rgb.green, rgb.blue)
            }
        }
    }

    @Test
    fun `applyRegionSpecificFilter inverts colors`() {
        val original = createSolidColorImage(5, 5, Color(50, 100, 150))
        val inverted = applyRegionSpecificFilter(original)

        for (x in 0 until original.width) {
            for (y in 0 until original.height) {
                val originalColor = Color(original.getRGB(x, y))
                val invertedColor = Color(inverted.getRGB(x, y))

                assertEquals(MAX_COLOR - originalColor.red, invertedColor.red)
                assertEquals(MAX_COLOR - originalColor.green, invertedColor.green)
                assertEquals(MAX_COLOR - originalColor.blue, invertedColor.blue)
            }
        }
    }

    @Test
    fun `blendSegment averages colors of two images`() {
        val img1 = createSolidColorImage(4, 4, Color(100, 150, 200))
        val img2 = createSolidColorImage(4, 4, Color(50, 50, 50))

        val blended = blendSegment(img1, img2)
        for (x in 0 until blended.width) {
            for (y in 0 until blended.height) {
                val c = Color(blended.getRGB(x, y))
                assertEquals((100 + 50) / 2, c.red)
                assertEquals((150 + 50) / 2, c.green)
                assertEquals((200 + 50) / 2, c.blue)
            }
        }
    }

    @Test
    fun `segmentImage splits image into correct number of segments`() {
        val image = createSolidColorImage(10, 10, Color.BLACK)
        val segments = segmentImage(image, 3)
        assertEquals(3, segments.size)
        val totalHeight = segments.sumOf { it.height }
        assertEquals(image.height, totalHeight)
        segments.forEach {
            assertEquals(image.width, it.width)
        }
    }

    @Test
    fun `mergeSegments recombines images vertically`() {
        val img1 = createSolidColorImage(4, 2, Color.RED)
        val img2 = createSolidColorImage(4, 3, Color.GREEN)
        val merged = mergeSegments(listOf(img1, img2), 4, 5)

        assertEquals(4, merged.width)
        assertEquals(5, merged.height)

        for (x in 0 until 4) {
            for (y in 0 until 2) {
                assertEquals(Color.RED.rgb, merged.getRGB(x, y))
            }
        }
        for (x in 0 until 4) {
            for (y in 2 until 5) {
                assertEquals(Color.GREEN.rgb, merged.getRGB(x, y))
            }
        }
    }
}
