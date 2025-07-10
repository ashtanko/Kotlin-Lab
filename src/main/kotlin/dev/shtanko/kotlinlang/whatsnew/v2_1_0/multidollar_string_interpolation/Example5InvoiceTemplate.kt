@file:Suppress("PackageNaming", "MagicNumber")

package dev.shtanko.kotlinlang.whatsnew.v2_1_0.multidollar_string_interpolation

fun invoiceTemplate(amount: Double): String = $$"""
    Dear Customer,

    Your total is $$${amount}.
    Please make the payment to account number: $12345678

    Thank you.
"""

fun main() {
    println(invoiceTemplate(99.99))
}
