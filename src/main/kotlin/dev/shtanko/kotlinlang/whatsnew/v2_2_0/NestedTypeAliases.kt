@file:Suppress("PackageNaming")

package dev.shtanko.kotlinlang.whatsnew.v2_2_0

class Dijkstra {

    data class Node(val id: String)

    typealias VisitedNodes = Set<Node>
    typealias Path = List<Node>
    typealias Cost = Int

    private fun step(visited: VisitedNodes, current: Node): Pair<Path, Cost> {
        // some fake logic
        return visited.toList() + current to visited.size
    }

    fun run() {
        val visited: VisitedNodes = setOf(Node("A"), Node("B"))
        val (path, cost) = step(visited, Node("C"))
        println("Path: $path, Cost: $cost")
    }
}

class HtmlBuilder {

    class Element(val name: String)

    typealias TagList = MutableList<Element>

    private val tags: TagList = mutableListOf()

    fun tag(name: String) {
        tags += Element(name)
    }

    fun render(): String = tags.joinToString(separator = "") { "<${it.name}/>" }
}

sealed class ApiResult<T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error<T>(val message: String) : ApiResult<T>()

    object Helper {
        typealias StringResult = ApiResult<String>
        typealias IntResult = ApiResult<Int>

        fun sample(): StringResult = Success("done")
    }
}

class FileProcessor {

    data class FileData(val name: String, val size: Int)

    typealias FileMap = Map<String, FileData>
    typealias Processor = (FileData) -> Unit

    fun processFiles(files: FileMap, processor: Processor) {
        for ((_, file) in files) {
            processor(file)
        }
    }
}

fun main() {
    val html = HtmlBuilder().apply {
        tag("div")
        tag("p")
    }.render()

    println(html) // <div/><p/>
}
