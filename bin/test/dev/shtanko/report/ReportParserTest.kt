/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.report

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import org.assertj.core.api.Assertions.assertThat
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ReportParserTest {

    @Test
    fun `fetchFilePath returns correct path when no arguments are provided`() {
        val expectedPath = System.getProperty("user.dir") + "/build/reports/detekt/detekt.html"
        assertEquals(expectedPath, fetchFilePath())
    }

    @Test
    fun `readContentFromFile reads correct content from file`() {
        val filePath = "src/test/resources/testFile.txt"
        val expectedContent = "This is a test file."
        val actual = readContentFromFile(filePath)
        assertThat(actual).contains(expectedContent)
    }

    @Test
    fun `parseHtmlToDocument returns correct Document object`() {
        val html = "<html><body><h2>Header</h2><li>Item</li></body></html>"
        val docHtml = Jsoup.parse(html).html()
        val actual = parseHtmlToDocument(html).html()
        assertThat(actual).isEqualTo(docHtml)
    }

    @Test
    fun `extractMetricsFromDocument returns correct metrics`() {
        val html = "<html><body><h2>Header</h2><li>Item</li></body></html>"
        val doc = Jsoup.parse(html)
        val expectedMetrics = mutableMapOf("Header" to mutableListOf("Item"))
        assertEquals(expectedMetrics, extractMetricsFromDocument(doc))
    }

    @Test
    fun `printMetrics prints correct output`() {
        val metrics = mapOf("Header" to listOf("Item"))
        val output = captureStandardOutput {
            printExtractedMetrics(metrics)
        }
        assertEquals("Header:\n  - Item\n", output)
    }

    @Test
    fun `printExtractedMetrics prints correct output`() {
        val metrics = mapOf("Header" to listOf("Item"))
        val output = captureStandardOutput {
            printExtractedMetrics(metrics)
        }
        assertEquals("Header:\n  - Item\n", output)
    }

    @Test
    fun `separateNumbersFromText separates numbers and text correctly`() {
        val input = "269 number of properties"
        val expectedOutput = Pair("269", " number of properties")

        val output = separateNumbersFromText(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `separateNumbersFromText handles multiple numbers correctly`() {
        val input = "123 cats and 456 dogs"
        val expectedOutput = Pair("123456", " cats and  dogs")

        val output = separateNumbersFromText(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `separateNumbersFromText handles no numbers correctly`() {
        val input = "No numbers here"
        val expectedOutput = Pair("", "No numbers here")

        val output = separateNumbersFromText(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `separateNumbersFromText handles only numbers correctly`() {
        val input = "123456"
        val expectedOutput = Pair("123456", "")

        val output = separateNumbersFromText(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `metricsToFile writes correct content to file`() {
        val metrics = mapOf("Header" to listOf("Item1", "Item2"))
        val tmpFile = File.createTempFile("testOutput", ".txt")
        val outputFilePath = tmpFile.path

        metricsToFile(metrics, outputFilePath)

        val expectedContent = "\n## Header:\n" +
            "* Item1\n" +
            "* Item2\n"
        val actualContent = File(outputFilePath).readText()

        assertEquals(expectedContent, actualContent)
    }

    @Test
    fun `metricsToFile handles empty metrics`() {
        val metrics = emptyMap<String, List<String>>()
        val tmpFile = File.createTempFile("testOutput", ".txt")
        val outputFilePath = tmpFile.path

        metricsToFile(metrics, outputFilePath)

        val expectedContent = ""
        val actualContent = File(outputFilePath).readText()

        assertEquals(expectedContent, actualContent)
    }

    @Test
    fun `metricsToFile handles multiple headers`() {
        val metrics = mapOf(
            "Header1" to listOf("Item1", "Item2"),
            "Header2" to listOf("Item3", "Item4"),
        )
        val tmpFile = File.createTempFile("testOutput", ".txt")
        val outputFilePath = tmpFile.path

        metricsToFile(metrics, outputFilePath)

        val expectedContent = "\n## Header1:\n* Item1\n* Item2\n\n## Header2:\n* Item3\n* Item4\n"
        val actualContent = File(outputFilePath).readText()

        assertEquals(expectedContent, actualContent)
    }

    @Test
    fun testGenerateMarkdownTable_withData() {
        val columns = listOf("Name", "Age", "City")
        val rows = listOf(
            listOf("Alice", "30", "New York"),
            listOf("Bob", "25", "Los Angeles"),
            listOf("Charlie", "35", "Chicago"),
        )
        val expectedHeader = "|Name   |Age|City       |"
        val expectedSeparator = "|-------|---|-----------|"
        val expectedRows = "|Alice  |30 |New York   |\n" +
            "|Bob    |25 |Los Angeles|\n" +
            "|Charlie|35 |Chicago    |"

        val (header, separator, dataRows) = generateMarkdownTable(columns, rows)

        println("$header\n$separator\n$dataRows")

        assertEquals(expectedHeader, header)
        assertEquals(expectedSeparator, separator)
        assertEquals(expectedRows, dataRows)
    }

    @Test
    fun generateMarkdownTableWithNewlineCharacter() {
        val columns = listOf("Header1", "Header2")
        val rows = listOf(
            listOf("Row1Col1", "Row1Col2"),
            listOf("Row2Col1", "Row2Col2"),
        )
        val (header, separator, dataRows) = generateMarkdownTable(columns, rows, newline = "\r\n")
        assertEquals("|Header1 |Header2 |", header)
        assertEquals("|--------|--------|", separator)
        assertEquals("|Row1Col1|Row1Col2|\r\n|Row2Col1|Row2Col2|", dataRows)
    }
}

fun captureStandardOutput(block: () -> Unit): String {
    val outputStream = ByteArrayOutputStream()
    System.setOut(PrintStream(outputStream))
    block()
    return outputStream.toString()
}
