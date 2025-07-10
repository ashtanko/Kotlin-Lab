@file:Suppress("PackageNaming")

package dev.shtanko.kotlinlang.whatsnew.v2_1_0.non_local_break_and_continue

fun analyzeData(data: List<String>) {
    for (item in data) {
        item.takeIf { it.isNotBlank() }?.also {
            if (it == "SKIP") {
                println("Skipping $it")
                continue
            }
            if (it == "STOP") {
                println("Stopping on $it")
                break
            }
            println("Processing $it")
        } ?: continue
    }
}

fun main() {
    val list = listOf("Alpha", "", "SKIP", "Beta", "STOP", "Gamma")
    analyzeData(list)
}
