/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

class DesignFoodRatingSystemTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(FoodRatingsPriorityQueue(foods, cuisines, ratings)),
            Arguments.of(FoodRatingsSortedSet(foods, cuisines, ratings)),
        )

        val foods =
            arrayOf("kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi")
        val cuisines = arrayOf("korean", "japanese", "japanese", "greek", "japanese", "korean")
        val ratings = intArrayOf(9, 12, 8, 15, 14, 7)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun foodRatingsTest(strategy: FoodRatings) {
        assertThat(strategy.highestRated("korean")).isEqualTo("kimchi")
        assertThat(strategy.highestRated("japanese")).isEqualTo("ramen")
        strategy.changeRating("sushi", 16)
        assertThat(strategy.highestRated("japanese")).isEqualTo("sushi")
        strategy.changeRating("ramen", 16)
        assertThat(strategy.highestRated("japanese")).isEqualTo("ramen")
    }
}
