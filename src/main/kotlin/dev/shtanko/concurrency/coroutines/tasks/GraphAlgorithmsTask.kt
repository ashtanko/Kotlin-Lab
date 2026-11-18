package dev.shtanko.concurrency.coroutines.tasks

import kotlin.random.Random
import kotlinx.coroutines.yield

class GraphAlgorithmsTask(
    private val vertices: Int = 1000,
) : BaseTask<String>(
    name = "Graph Algorithms",
    description = "Finding shortest paths in $vertices vertices",
) {
    override suspend fun execute(): String {
        // Create adjacency matrix with random weights
        val graph = Array(vertices) { IntArray(vertices) { 0 } }
        val edges = vertices * 10 // Create sparse graph

        // Generate random edges
        repeat(edges) {
            val u = Random.nextInt(vertices)
            val v = Random.nextInt(vertices)
            if (u != v) {
                graph[u][v] = Random.nextInt(1, 100)
                graph[v][u] = graph[u][v] // Undirected graph
            }
        }

        // Run Dijkstra's algorithm from vertex 0
        val distances = dijkstra(graph, 0)

        // Find diameter (longest shortest path)
        val maxDistance = distances.maxOrNull() ?: 0
        val avgDistance = distances.filter { it < Int.MAX_VALUE }.average()

        return "Max dist: $maxDistance, Avg: %.2f".format(avgDistance)
    }

    private suspend fun dijkstra(graph: Array<IntArray>, start: Int): IntArray {
        val dist = IntArray(vertices) { Int.MAX_VALUE }
        val visited = BooleanArray(vertices)
        dist[start] = 0

        for (count in 0 until vertices - 1) {
            if (count % 50 == 0) {
                yield()
                updateProgress(count.toFloat() / vertices)
            }

            val u = minDistance(dist, visited)
            visited[u] = true

            for (v in 0 until vertices) {
                if (!visited[v] && graph[u][v] != 0 &&
                    dist[u] != Int.MAX_VALUE &&
                    dist[u] + graph[u][v] < dist[v]
                ) {
                    dist[v] = dist[u] + graph[u][v]
                }
            }
        }

        return dist
    }

    private fun minDistance(dist: IntArray, visited: BooleanArray): Int {
        var min = Int.MAX_VALUE
        var minIndex = -1

        for (v in 0 until vertices) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v]
                minIndex = v
            }
        }

        return minIndex
    }
}
