@file:Suppress("PackageNaming")

package dev.shtanko.kotlinlang.whatsnew.v2_1_0.multidollar_string_interpolation

fun generateScript(user: String): String = $$$"""
    #!/bin/bash
    echo "Starting script..."
    echo "Current user is $$$$user"
    echo "Literal \$PATH = \$PATH"
"""

fun main() {
    println(generateScript("admin"))
}
