package dev.shtanko.algorithms.sorts

class SelectionSortTest : AbstractSortTest<SelectionSort>(SelectionSort())

class StableSelectionSortTest : AbstractSortTest<StableSelectionSort>(StableSelectionSort())

class ShellSortTest : AbstractSortTest<ShellSort>(ShellSort())

class QuickSortTest : AbstractSortTest<QuickSort>(QuickSort())

class MergeSortTest : AbstractSortTest<MergeSort>(MergeSort())

class InsertionSortTest : AbstractSortTest<InsertionSort>(InsertionSort())

class InsertionSort2Test : AbstractSortTest<InsertionSort2>(InsertionSort2())

class HeapSortTest : AbstractSortTest<HeapSort>(HeapSort())

class BubbleSortTest : AbstractSortTest<BubbleSort>(BubbleSort())

class SimpleBubbleSortTest : AbstractSortTest<SimpleBubbleSort>(SimpleBubbleSort())

class ArraySortTest : AbstractSortTest<ArraySort>(ArraySort())

class PancakeSortTest : AbstractSortTest<PancakeSort>(PancakeSort())

class GnomeSortTest : AbstractSortTest<GnomeSort>(GnomeSort())
