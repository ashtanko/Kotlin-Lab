package dev.shtanko.algorithms.codingbat.string2

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class PlusOutTest<out T : PlusOut>(private val strategy: T) {
    private object InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments?>? = Stream.of(
            Arguments.of("", "", ""),
            Arguments.of("12xy34", "xy", "++xy++"),
            Arguments.of("12xy34", "1", "1+++++"),
            Arguments.of("12xy34xyabcxy", "xy", "++xy++xy+++xy"),
            Arguments.of("abXYabcXYZ", "ab", "ab++ab++++"),
            Arguments.of("abXYabcXYZ", "abc", "++++abc+++"),
            Arguments.of("abXYabcXYZ", "XY", "++XY+++XY+"),
            Arguments.of("abXYxyzXYZ", "XYZ", "+++++++XYZ"),
            Arguments.of("--++ab", "++", "++++++"),
            Arguments.of("aaxxxxbb", "xx", "++xxxx++"),
            Arguments.of("123123", "3", "++3++3"),
        )
    }

    @ArgumentsSource(InputArgumentsProvider::class)
    @ParameterizedTest
    fun `plusOut test`(str: String, word: String, expected: String) {
        assertThat(strategy.invoke(str, word)).isEqualTo(expected)
    }
}

class PlusOutSlidingWindowTest : PlusOutTest<PlusOut>(PlusOut.SlidingWindow)
