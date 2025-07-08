@file:Suppress("PackageNaming")

package dev.shtanko.kotlinlang.whatsnew.v2_2_0

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DijkstraTest {

    @Test
    fun testStepFunction() {
        val dijkstra = Dijkstra()
        val visited = setOf(Dijkstra.Node("A"), Dijkstra.Node("B"))
        val result = dijkstra.run {
            // Exposing internal function for test (you may mark it internal or move logic)
            val method = Dijkstra::class.java.getDeclaredMethod("step", Set::class.java, Dijkstra.Node::class.java)
            method.isAccessible = true
            method.invoke(this, visited, Dijkstra.Node("C")) as Pair<*, *>
        }

        val path = result.first as List<*>
        val cost = result.second as Int
        assertEquals(3, path.size)
        assertEquals(2, cost)
    }
}

class HtmlBuilderTest {

    @Test
    fun testRenderHtml() {
        val html = HtmlBuilder().apply {
            tag("h1")
            tag("p")
        }.render()

        assertEquals("<h1/><p/>", html)
    }
}

class ApiResultTest {

    @Test
    fun testStringResultAlias() {
        val result = ApiResult.Helper.sample()
        assertTrue(result is ApiResult.Success)
        assertEquals("done", (result as ApiResult.Success).data)
    }
}

class FileProcessorTest {

    @Test
    fun testProcessFiles() {
        val processor = FileProcessor()
        val files = mapOf(
            "f1" to FileProcessor.FileData("f1", 100),
            "f2" to FileProcessor.FileData("f2", 200),
        )

        val processed = mutableListOf<String>()
        processor.processFiles(files) {
            processed += it.name
        }

        assertEquals(listOf("f1", "f2"), processed)
    }
}
