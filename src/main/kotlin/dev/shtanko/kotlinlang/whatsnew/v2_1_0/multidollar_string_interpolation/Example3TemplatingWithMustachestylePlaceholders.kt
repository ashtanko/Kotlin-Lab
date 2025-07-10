@file:Suppress("PackageNaming")

package dev.shtanko.kotlinlang.whatsnew.v2_1_0.multidollar_string_interpolation

fun template(title: String): String = $$"""
    <html>
      <head><title>$${title}</title></head>
      <body>
        <h1>Welcome to $${title}</h1>
        <p>This is a literal placeholder: {{$title}}</p>
      </body>
    </html>
"""

fun main() {
    println(template("Kotlin News"))
}
