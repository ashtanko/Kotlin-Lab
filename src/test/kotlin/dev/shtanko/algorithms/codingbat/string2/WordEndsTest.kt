package dev.shtanko.algorithms.codingbat.string2

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class WordEndsTest<out T : WordEnds>(private val strategy: T) {
    private object InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments?>? = Stream.of(
            Arguments.of("abcXY123XYijk", "XY", "c13i"),
            Arguments.of("XY123XY", "XY", "13"),
            Arguments.of("XY1XY", "XY", "11"),
            Arguments.of("XXY1XY", "XY", "X11"),
            Arguments.of("XY", "XY", ""),
            Arguments.of("XYXY", "XY", "XY"),
        )
    }

    @ArgumentsSource(InputArgumentsProvider::class)
    @ParameterizedTest
    fun `word ends test`(str: String, word: String, expected: String) {
        assertThat(strategy.invoke(str, word)).isEqualTo(expected)
    }
}

class WordEndsSlidingWindowTest : WordEndsTest<WordEnds>(WordEnds.SlidingWindow)
class WordEndsSubstringScanningTest : WordEndsTest<WordEnds>(WordEnds.SubstringScanning)
class WordEndsSlidingWindowOptimizedTest : WordEndsTest<WordEnds>(WordEnds.SlidingWindowOptimized)
