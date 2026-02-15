@file:Suppress("MagicNumber")

package dev.shtanko.concurrency.coroutines.tasks

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val viewModel = TaskViewModel()

    viewModel.addTask(PrimeCalculationTask(5000_0000))
    viewModel.addTask(MatrixMultiplicationTask(2000))
    viewModel.addTask(MandelbrotTask(10000, 10000))
    viewModel.addTask(SortingTask(100_000))
    viewModel.addTask(HashComputationTask(10000_0000))

    viewModel.addTask(CryptographicTask(50000))
    viewModel.addTask(CompressionTask(50_0000))
    viewModel.addTask(BinaryTreeTask(30_0000))
    viewModel.addTask(GraphAlgorithmsTask(5000))
    viewModel.addTask(StringMatchingTask(500_0000, 50))
    viewModel.addTask(ImageProcessingTask(600, 400))
    viewModel.addTask(NeuralNetworkTask(epochs = 5))
    viewModel.addTask(MergeSortTask(900_000))
    viewModel.addTask(QuickSortTask(900_000))

    launch {
        viewModel.tasks.collect {
            println("TASK: ${it}")
        }
    }

    launch {
        viewModel.results.collect {
            println("RES: ${it}")
        }
    }

    viewModel.runAllTasks()
}
