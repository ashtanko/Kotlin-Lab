@file:Suppress("PackageNaming")

package dev.shtanko.kotlinlang.whatsnew.v2_1_0.multidollar_string_interpolation

fun buildQuery(table: String, column: String): String = $$"""
    SELECT "$$column"
    FROM "$$table"
    WHERE "$column" IS NOT NULL;
"""

fun main() {
    println(buildQuery("users", "email"))
}
