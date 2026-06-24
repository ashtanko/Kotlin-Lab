package dev.shtanko.concurrency.coroutines.tasks

import kotlin.math.exp
import kotlin.random.Random
import kotlinx.coroutines.yield

class NeuralNetworkTask(
    private val inputSize: Int = 784,
    private val hiddenSize: Int = 128,
    private val outputSize: Int = 10,
    private val epochs: Int = 10,
) : BaseTask<Double>(
    name = "Neural Network Training",
    description = "Training ${hiddenSize}-node network for $epochs epochs",
) {
    override suspend fun execute(): Double {
        // Initialize weights
        val weightsIH = Array(inputSize) { DoubleArray(hiddenSize) { Random.nextDouble(-1.0, 1.0) } }
        val weightsHO = Array(hiddenSize) { DoubleArray(outputSize) { Random.nextDouble(-1.0, 1.0) } }

        // Training samples
        val samples = 100
        var totalLoss = 0.0

        for (epoch in 0 until epochs) {
            for (sample in 0 until samples) {
                if ((epoch * samples + sample) % 50 == 0) {
                    yield()
                    updateProgress((epoch * samples + sample).toFloat() / (epochs * samples))
                }

                // Forward pass
                val input = DoubleArray(inputSize) { Random.nextDouble() }
                val hidden = DoubleArray(hiddenSize)
                val output = DoubleArray(outputSize)

                // Input to hidden
                for (h in 0 until hiddenSize) {
                    var sum = 0.0
                    for (i in 0 until inputSize) {
                        sum += input[i] * weightsIH[i][h]
                    }
                    hidden[h] = sigmoid(sum)
                }

                // Hidden to output
                for (o in 0 until outputSize) {
                    var sum = 0.0
                    for (h in 0 until hiddenSize) {
                        sum += hidden[h] * weightsHO[h][o]
                    }
                    output[o] = sigmoid(sum)
                }

                // Calculate loss
                val target = DoubleArray(outputSize) { if (it == sample % outputSize) 1.0 else 0.0 }
                for (o in 0 until outputSize) {
                    totalLoss += (target[o] - output[o]) * (target[o] - output[o])
                }
            }
        }

        return totalLoss / (epochs * samples)
    }

    private fun sigmoid(x: Double): Double = 1.0 / (1.0 + exp(-x))
}
