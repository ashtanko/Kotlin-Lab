@file:Suppress("PackageNaming")

package dev.shtanko.kotlinlang.whatsnew.v2_1_0.non_local_break_and_continue

fun processList(elements: List<Int>): Boolean {
    for (element in elements) {
        val variable = element.takeIf { it > 0 } ?: run {
            println("Invalid element $element, skipping...")
            continue  // skips this iteration
        }

        if (variable == 0) return true
        println("Processing: $variable")
    }
    return false
}

fun main() {
    val list = listOf(1, -2, 3, 0, 4)
    processList(list)
}
