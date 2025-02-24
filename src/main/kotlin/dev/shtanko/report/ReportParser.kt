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

import java.io.File
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

// Type alias for a mutable map with String keys and mutable list of String values
typealias MetricsMap = MutableMap<String, MutableList<String>>

/**
 * Main function that reads a detekt report file and extracts metrics from it.
 * args[0] detekt report file path
 * args[1] formatted output file path
 */
fun main(args: Array<String>) {
    val filePath = args.takeIf { it.isNotEmpty() }?.first() ?: fetchFilePath()
    val outputFilePath = args.takeIf { it.isNotEmpty() && it.size > 1 }?.get(1) ?: fetchFileOutputPath()
    val html = readContentFromFile(filePath)
    val doc = parseHtmlToDocument(html)
    val metrics = extractMetricsFromDocument(doc)
    val md = generateMarkdownTable(metrics)
    println(md)
    metricsToFile(metrics, outputFilePath)
    mdToFile(md, fetchDirPath())
    printExtractedMetrics(metrics)

    metrics[METRICS_KEY]?.forEach { handleMetric(separateNumbersFromText(it.formatMetricValue())) }
    metrics[COMPLEXITY_REPORT_KEY]?.forEach { handleMetric(separateNumbersFromText(it.formatMetricValue())) }
}

fun mdToFile(md: String, outputFilePath: String) {
    val file = File(outputFilePath)
    val targetFile = if (file.isDirectory) {
        File("${file.absolutePath}/output2.md")
    } else {
        file
    }

    if (!targetFile.exists()) targetFile.createNewFile()
    targetFile.writeText("")
    targetFile.appendText(md)
}

fun metricsToFile(metrics: Map<String, List<String>>, outputFilePath: String) {
    val file = File(outputFilePath)
    val targetFile = if (file.isDirectory) {
        File("${file.absolutePath}/output.md")
    } else {
        file
    }

    if (!targetFile.exists()) targetFile.createNewFile()
    targetFile.writeText("")

    writeMetricsToFile(metrics, targetFile)
}

private fun writeMetricsToFile(metrics: Map<String, List<String>>, file: File) {
    metrics.forEach { (header, items) ->
        file.appendText("\n## $header:\n")
        items.forEach { item ->
            file.appendText("* $item\n")
        }
    }
}

fun generateMarkdownTable(metrics: Map<String, List<String>>): String {
    val table = StringBuilder()

    // Header
    table.append("| ${metrics.keys.joinToString(" | ")} |\n")
    table.append("| ${metrics.keys.joinToString(" | ") { "--------" }} |\n")

    // Rows
    val rows = metrics.values.first().size
    for (i in 0 until rows) {
        val row = metrics.values.map { it.getOrNull(i) ?: "" }
        table.append("| ${row.joinToString(" | ")} |\n")
    }

    return table.toString()
}

fun fetchDirPath(): String {
    val currentDir = System.getProperty("user.dir")
    return "$currentDir/build/reports/detekt"
}

/**
 * Returns the file path of the detekt report.
 */
fun fetchFilePath(): String {
    val currentDir = System.getProperty("user.dir")
    return "$currentDir/build/reports/detekt/detekt.html"
}

fun fetchFileOutputPath(): String {
    val currentDir = System.getProperty("user.dir")
    return "$currentDir/build/reports/detekt/detekt_report.txt"
}

/**
 * Reads the content of a file at the given file path.
 */
fun readContentFromFile(filePath: String): String = File(filePath).readText()

/**
 * Parses the given HTML string into a Document object.
 */
fun parseHtmlToDocument(html: String): Document = Jsoup.parse(html)

/**
 * Extracts metrics from the given Document object.
 * The metrics are stored in a map where the keys are the metric headers and the values are lists of metric items.
 */
fun extractMetricsFromDocument(doc: Document): Map<String, List<String>> {
    val tags = doc.select("$TAG_H2, $TAG_LI, $TAG_UL")
    return handleTags(tags)
}

fun handleTags(tags: Elements): MetricsMap {
    val metrics: MetricsMap = mutableMapOf()
    var currentHeader: String? = null
    tags.forEach { tag ->
        when (tag.tagName()) {
            TAG_H2 -> {
                currentHeader = tag.text()
                currentHeader?.let { metrics[it] = mutableListOf() }
            }

            TAG_LI -> currentHeader?.let { header ->
                metrics[header]?.add(tag.text())
            }

            TAG_UL -> handleUlTag(currentHeader, tag, metrics)
        }
    }
    return metrics
}

fun handleUlTag(currentHeader: String?, tag: Element, metrics: MetricsMap): MetricsMap {
    val separatedNumbersAndText = tag.children().map { separateNumbersFromText(it.text()) }
    when (currentHeader?.lowercase()) {
        METRICS_KEY.lowercase() -> {
            separatedNumbersAndText.forEach { (numbers, text) ->
                metrics[currentHeader]?.add("$numbers $text")
            }
        }

        COMPLEXITY_REPORT_KEY.lowercase() -> {
            separatedNumbersAndText.forEach { (numbers, text) ->
                metrics[currentHeader]?.add("$numbers $text")
            }
        }

        else -> {
            addMetrics(tag, metrics[currentHeader])
        }
    }
    return metrics
}

fun addMetrics(tag: Element, metrics: MutableList<String>?): MutableList<String> {
    tag.children().map { separateNumbersFromText(it.text()) }.forEach { (numbers, text) ->
        metrics?.add("$numbers $text")
    }
    return metrics ?: mutableListOf()
}

/**
 * Prints the given metrics.
 * Each metric is printed with its header and its items.
 */
fun printExtractedMetrics(metrics: Map<String, List<String>>) {
    for ((header, items) in metrics) {
        println("$header:")
        for (item in items) {
            println("  - $item")
        }
    }
}

/**
 * Separates numbers and text in the given input string.
 * Returns a pair where the first element is the numbers and the second element is the text.
 */
fun separateNumbersFromText(input: String): Pair<String, String> {
    val pattern = "(\\d+)|(\\D+)".toRegex()
    val matches = pattern.findAll(input)

    val numbers = matches.filter { it.groupValues[1].isNotEmpty() }.joinToString("") { it.groupValues[1] }
    val text = matches.filter { it.groupValues[2].isNotEmpty() }.joinToString("") { it.groupValues[2] }

    return Pair(numbers, text)
}

fun generateMarkdownTable(
    columns: List<String>,
    rows: List<List<String>>,
    newline: String = "\n",
): Triple<String, String, String> {
    if (columns.isEmpty()) return Triple("", "", "")

    // Calculate the maximum width for each column
    val columnWidths = columns.map { it.length }.toMutableList()
    rows.forEach { row ->
        row.forEachIndexed { index, cell ->
            if (index < columnWidths.size) {
                columnWidths[index] = maxOf(columnWidths[index], cell.length)
            }
        }
    }

    // Helper function to pad cells to match the column width
    fun padCell(cell: String, width: Int): String = cell.padEnd(width)

    // Generate the header row
    val header = columns.mapIndexed { index, col ->
        padCell(col, columnWidths[index])
    }.joinToString("|", prefix = "|", postfix = "|")

    // Generate the separator row
    val separator = columnWidths.map {
        "-".repeat(it).padEnd(it)
    }
        .joinToString("|", prefix = "|", postfix = "|")

    // Generate the data rows
    val dataRows = rows.joinToString(newline) { row ->
        row.mapIndexed { index, cell ->
            padCell(cell, columnWidths[index])
        }.joinToString("|", prefix = "|", postfix = "|")
    }

    // Combine header, separator, and rows into the final Markdown table
    return Triple(header, separator, dataRows)
}

data class Metric(
    val key: String,
    val value: String,
)

fun String.formatMetricValue(): String {
    return replace(",", "").replace("\\s".toRegex(), "")
}

fun handleMetric(metric: Pair<String, String>) {
    println(metric.second)
}

private const val TAG_H2 = "h2"
private const val TAG_LI = "li"
private const val TAG_UL = "ul"

private const val METRICS_KEY = "Metrics"
private const val COMPLEXITY_REPORT_KEY = "Complexity Report"

private val PROPERTIES_KEY = "properties" to "Props"
private val FUNCTIONS_KEY = "functions" to "Funcs"
private val CLASSES_KEY = "classes" to "Classes"
private val PACKAGES_KEY = "packages" to "Pkgs"
private val KT_FILES_KEY = "kt files" to "Kt Files"

private val LOC_KEY = "loc" to "Lines of Code"
private val SLOC_KEY = "sloc" to "Source Lines of Code"
private val LLOC_KEY = "lloc" to "Logical Lines of Code"
private val CLOC_KEY = "cloc" to "Comment Lines of Code"
private val MCC_KEY = "mcc" to "Maintainability Complexity"
private val CC_KEY = "cognitive complexity" to "Cognitive Complexity"
private val CSR_KEY = "comment source ratio" to "Comment Source Ratio"
private val MCC_PER_LLOC_KEY = "mcc per lloc" to "Maintainability Complexity per Logical Lines of Code"
private val CS_KEY = "code smells" to "Code Smells"
