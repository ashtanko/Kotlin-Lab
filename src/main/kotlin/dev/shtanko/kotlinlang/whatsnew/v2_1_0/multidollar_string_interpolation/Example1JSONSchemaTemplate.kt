@file:Suppress("PackageNaming")

package dev.shtanko.kotlinlang.whatsnew.v2_1_0.multidollar_string_interpolation

import kotlin.reflect.KClass

val KClass<*>.jsonSchema: String
    get() = $$"""
    {
      "$schema": "https://json-schema.org/draft/2020-12/schema",
      "$id": "https://example.com/product.schema.json",
      "$dynamicAnchor": "meta",
      "title": "$${simpleName ?: qualifiedName ?: "unknown"}",
      "supertypes": "$$supertypes",
      "type": "object"
    }
    """

fun main() {
    println(String::class.jsonSchema)
    println(Int::class.jsonSchema)
    println(Nothing::class.jsonSchema)
}
