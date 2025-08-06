package dev.shtanko.concurrency.coroutines.examples

import java.awt.Color
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ImageProcessingTest {
    @Test
    fun `toGrayscale should convert image to shades of gray`() {
        val original = loadTestImage()
        val gray = toGrayscale(original)

        for (x in 0 until gray.width) {
            for (y in 0 until gray.height) {
                val rgb = gray.getRGB(x, y)
                val r = (rgb shr 16) and 0xFF
                val g = (rgb shr 8) and 0xFF
                val b = rgb and 0xFF
                assertEquals(r, g)
                assertEquals(g, b)
            }
        }
    }

    @Test
    fun `applySepia should produce warmer tones`() {
        val original = loadTestImage()
        val sepia = applySepia(original)

        assertEquals(original.width, sepia.width)
        assertEquals(original.height, sepia.height)
    }

    @Test
    fun `invertColors should invert each RGB channel`() {
        val original = loadTestImage()
        val inverted = invertColors(original)

        for (x in 0 until original.width) {
            for (y in 0 until original.height) {
                val oRgb = original.getRGB(x, y)
                val iRgb = inverted.getRGB(x, y)

                val or = (oRgb shr 16) and 0xFF
                val og = (oRgb shr 8) and 0xFF
                val ob = oRgb and 0xFF

                val ir = (iRgb shr 16) and 0xFF
                val ig = (iRgb shr 8) and 0xFF
                val ib = iRgb and 0xFF

                assertEquals(255 - or, ir)
                assertEquals(255 - og, ig)
                assertEquals(255 - ob, ib)
            }
        }
    }

    @Test
    fun `adjustBrightness should increase brightness`() {
        val original = loadTestImage()
        val brightened = adjustBrightness(original)

        for (x in 0 until original.width) {
            for (y in 0 until original.height) {
                val oRgb = original.getRGB(x, y)
                val bRgb = brightened.getRGB(x, y)

                val or = (oRgb shr 16) and 0xFF
                val br = (bRgb shr 16) and 0xFF
                assertTrue(br >= or)
            }
        }
    }

    @Test
    fun `toBlackAndWhite should threshold to black or white`() {
        val original = loadTestImage()
        val bw = toBlackAndWhite(original, threshold = 128)

        for (x in 0 until bw.width) {
            for (y in 0 until bw.height) {
                val rgb = bw.getRGB(x, y)
                val r = (rgb shr 16) and 0xFF
                val g = (rgb shr 8) and 0xFF
                val b = rgb and 0xFF
                assertTrue(setOf(0, 255).containsAll(listOf(r, g, b)))
            }
        }
    }

    @Test
    fun `mirrorHorizontal should flip image left to right`() {
        val original = loadTestImage()
        val mirrored = mirrorHorizontal(original)

        for (x in 0 until original.width) {
            for (y in 0 until original.height) {
                assertEquals(original.getRGB(x, y), mirrored.getRGB(original.width - x - 1, y))
            }
        }
    }

    @Test
    fun `flipVertical should flip image top to bottom`() {
        val original = loadTestImage()
        val flipped = flipVertical(original)

        for (x in 0 until original.width) {
            for (y in 0 until original.height) {
                assertEquals(original.getRGB(x, y), flipped.getRGB(x, original.height - y - 1))
            }
        }
    }

    @Test
    fun `rotate90 should rotate image clockwise`() {
        val original = loadTestImage()
        val rotated = rotate90(original)

        assertEquals(original.width, rotated.height)
        assertEquals(original.height, rotated.width)

        for (x in 0 until original.width) {
            for (y in 0 until original.height) {
                assertEquals(original.getRGB(x, y), rotated.getRGB(y, original.width - x - 1))
            }
        }
    }

    @Test
    fun `blur with one iteration should change pixels`() {
        val input = createTestImage()
        val output = blur(input, iterations = 1)

        assertEquals(input.width, output.width)
        assertEquals(input.height, output.height)

        val centerBefore = Color(input.getRGB(1, 1))
        val centerAfter = Color(output.getRGB(1, 1))

        assertNotEquals(centerBefore.red, centerAfter.red)
        assertNotEquals(centerBefore.green, centerAfter.green)
        assertNotEquals(centerBefore.blue, centerAfter.blue)
    }

    @Test
    fun `blur with zero iterations returns same image`() {
        val input = createTestImage()
        val output = blur(input, iterations = 0)

        for (x in 0 until input.width) {
            for (y in 0 until input.height) {
                assertEquals(input.getRGB(x, y), output.getRGB(x, y), "Pixel mismatch at ($x, $y)")
            }
        }
    }

    @Test
    fun `multiple blur iterations produce stronger smoothing`() {
        val input = createTestImage()

        val onceBlurred = blur(input, iterations = 1)
        val twiceBlurred = blur(input, iterations = 2)

        val centerOnce = Color(onceBlurred.getRGB(1, 1))
        val centerTwice = Color(twiceBlurred.getRGB(1, 1))

        assertNotEquals(centerOnce.red, centerTwice.red)
        assertNotEquals(centerOnce.green, centerTwice.green)
        assertNotEquals(centerOnce.blue, centerTwice.blue)
    }

    private fun loadTestImage(name: String = "images/test_image.jpg"): BufferedImage {
        val resource = Thread.currentThread().contextClassLoader.getResource(name)
            ?: error("Test image not found")
        return ImageIO.read(resource)
    }

    private fun createTestImage(): BufferedImage {
        val img = BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB)
        img.setRGB(0, 0, Color(255, 0, 0).rgb)   // Red
        img.setRGB(1, 0, Color(0, 255, 0).rgb)   // Green
        img.setRGB(2, 0, Color(0, 0, 255).rgb)   // Blue

        img.setRGB(0, 1, Color(255, 255, 0).rgb) // Yellow
        img.setRGB(1, 1, Color(0, 255, 255).rgb) // Cyan
        img.setRGB(2, 1, Color(255, 0, 255).rgb) // Magenta

        img.setRGB(0, 2, Color(255, 255, 255).rgb) // White
        img.setRGB(1, 2, Color(128, 128, 128).rgb) // Gray
        img.setRGB(2, 2, Color(0, 0, 0).rgb)       // Black

        return img
    }
}
