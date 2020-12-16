/*
 * Copyright 2020 Alexey Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.coursera

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

class MaxWeightPathEvaluatorTest {
    companion object {
        private const val FILE_NAME = "src/test/resources/mwis.txt"
    }

    @Test
    fun `max weight path evaluator on file test`() {
        val weights = Files.lines(Paths.get(FILE_NAME)).skip(1).mapToLong { it.toLong() }.toArray()
        val evaluator = MaxWeightPathEvaluator(weights)
        val maxWeight = evaluator.evaluate()
        assertEquals(2955353732, maxWeight)
        val path = evaluator.getPath()
        val expected = setOf(
            1,
            3,
            5,
            8,
            10,
            13,
            15,
            18,
            20,
            22,
            24,
            26,
            28,
            31,
            33,
            36,
            38,
            40,
            42,
            44,
            46,
            48,
            50,
            52,
            54,
            56,
            58,
            60,
            62,
            64,
            66,
            69,
            72,
            75,
            77,
            79,
            81,
            83,
            85,
            88,
            90,
            92,
            94,
            96,
            98,
            100,
            103,
            106,
            108,
            110,
            112,
            115,
            117,
            120,
            122,
            124,
            126,
            128,
            131,
            133,
            136,
            139,
            141,
            143,
            145,
            147,
            149,
            151,
            153,
            155,
            157,
            160,
            162,
            164,
            166,
            168,
            170,
            173,
            175,
            177,
            179,
            181,
            183,
            185,
            187,
            190,
            193,
            195,
            197,
            199,
            201,
            203,
            205,
            207,
            209,
            211,
            214,
            216,
            218,
            221,
            223,
            226,
            228,
            230,
            232,
            234,
            236,
            238,
            240,
            243,
            245,
            247,
            249,
            252,
            254,
            256,
            258,
            261,
            263,
            265,
            267,
            269,
            271,
            273,
            275,
            277,
            279,
            281,
            283,
            285,
            287,
            289,
            292,
            294,
            296,
            298,
            300,
            302,
            304,
            306,
            308,
            310,
            312,
            314,
            316,
            318,
            321,
            323,
            325,
            327,
            329,
            331,
            333,
            335,
            337,
            339,
            341,
            343,
            345,
            347,
            349,
            351,
            353,
            355,
            357,
            359,
            361,
            363,
            365,
            367,
            369,
            371,
            373,
            375,
            377,
            379,
            381,
            383,
            385,
            387,
            389,
            391,
            393,
            395,
            397,
            399,
            402,
            404,
            406,
            408,
            410,
            413,
            415,
            417,
            420,
            422,
            425,
            427,
            429,
            431,
            433,
            435,
            437,
            439,
            441,
            443,
            445,
            447,
            449,
            451,
            454,
            456,
            458,
            460,
            462,
            464,
            466,
            468,
            470,
            472,
            474,
            476,
            478,
            481,
            483,
            485,
            488,
            490,
            492,
            494,
            496,
            498,
            500,
            503,
            505,
            508,
            510,
            512,
            514,
            517,
            519,
            521,
            523,
            525,
            527,
            529,
            531,
            534,
            537,
            539,
            541,
            543,
            545,
            548,
            550,
            552,
            554,
            556,
            558,
            560,
            563,
            566,
            569,
            571,
            573,
            575,
            577,
            579,
            581,
            584,
            586,
            588,
            590,
            592,
            594,
            596,
            598,
            600,
            602,
            604,
            606,
            608,
            610,
            613,
            615,
            617,
            619,
            621,
            623,
            625,
            628,
            630,
            632,
            634,
            637,
            639,
            641,
            643,
            645,
            647,
            649,
            652,
            654,
            656,
            658,
            660,
            662,
            664,
            666,
            669,
            671,
            673,
            676,
            679,
            682,
            684,
            686,
            688,
            690,
            692,
            695,
            697,
            699,
            702,
            704,
            706,
            708,
            710,
            713,
            715,
            717,
            719,
            721,
            723,
            726,
            728,
            730,
            733,
            735,
            737,
            739,
            741,
            743,
            745,
            748,
            750,
            752,
            755,
            757,
            760,
            763,
            766,
            768,
            770,
            772,
            774,
            776,
            778,
            781,
            784,
            786,
            788,
            790,
            793,
            795,
            797,
            799,
            801,
            803,
            806,
            809,
            812,
            814,
            816,
            819,
            821,
            823,
            825,
            827,
            829,
            832,
            834,
            837,
            839,
            841,
            843,
            846,
            849,
            851,
            853,
            855,
            857,
            859,
            861,
            864,
            866,
            868,
            871,
            873,
            876,
            878,
            880,
            882,
            884,
            886,
            888,
            890,
            892,
            894,
            896,
            898,
            900,
            903,
            905,
            907,
            909,
            911,
            913,
            915,
            917,
            919,
            921,
            924,
            927,
            929,
            932,
            934,
            936,
            938,
            940,
            942,
            944,
            946,
            948,
            950,
            952,
            954,
            956,
            958,
            960,
            962,
            964,
            966,
            969,
            971,
            973,
            975,
            977,
            979,
            981,
            983,
            985,
            987,
            989,
            991,
            993,
            995,
            998,
            1000
        )
        assertEquals(expected, path)
        assertEquals(setOf(1, 3, 117, 517), path.intersect(listOf(1, 2, 3, 4, 17, 117, 517, 997)))
    }
}
