/*
 * Copyright 2020 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.sorts

internal class SelectionSortTest : AbstractSortTest<AbstractSortStrategy>(SelectionSort())

internal class StableSelectionSortTest : AbstractSortTest<AbstractSortStrategy>(StableSelectionSort())

internal class ShellSortTest : AbstractSortTest<AbstractSortStrategy>(ShellSort())

internal class QuickSortTest : AbstractSortTest<AbstractSortStrategy>(QuickSort())

internal class MergeSortTest : AbstractSortTest<AbstractSortStrategy>(MergeSort())

internal class InsertionSortTest : AbstractSortTest<AbstractSortStrategy>(InsertionSort())

internal class InsertionSort2Test : AbstractSortTest<AbstractSortStrategy>(InsertionSort2())

internal class HeapSortTest : AbstractSortTest<AbstractSortStrategy>(HeapSort())

internal class BubbleSortTest : AbstractSortTest<AbstractSortStrategy>(BubbleSort())

internal class SimpleBubbleSortTest : AbstractSortTest<AbstractSortStrategy>(SimpleBubbleSort())

internal class ArraySortTest : AbstractSortTest<AbstractSortStrategy>(ArraySort())

internal class PancakeSortTest : AbstractSortTest<AbstractSortStrategy>(PancakeSort())

internal class GnomeSortTest : AbstractSortTest<AbstractSortStrategy>(GnomeSort())

internal class QuickSortRecursiveTest2 : AbstractSortTest<AbstractSortStrategy>(QuickSortRecursive())

internal class BinarySortTest : AbstractSortTest<AbstractSortStrategy>(BinarySort())
