package dev.shtanko.algorithms.leetcode

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

internal abstract class PaintFenceTest<out T : PaintFence>(private val strategy: T) {

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(0, 0, 0),
            Arguments.of(3, 2, 6),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `num ways test`(n: Int, k: Int, expected: Int) {
        val actual = strategy.numWays(n, k)
        assertThat(actual, equalTo(expected))
    }
}

internal class PaintFence1DTopDownTest : PaintFenceTest<PaintFence1DTopDown>(PaintFence1DTopDown())
internal class PaintFence1DBottomUpTest : PaintFenceTest<PaintFence1DBottomUp>(PaintFence1DBottomUp())
internal class PaintFence2DBottomUpTest : PaintFenceTest<PaintFence2DBottomUp>(PaintFence2DBottomUp())