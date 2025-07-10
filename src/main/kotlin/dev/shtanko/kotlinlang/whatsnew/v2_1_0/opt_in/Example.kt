@file:Suppress("PackageNaming")

package dev.shtanko.kotlinlang.whatsnew.v2_1_0.opt_in

// Library Code (internal module or published API)
@RequiresOptIn(level = RequiresOptIn.Level.WARNING, message = "May change in future versions")
annotation class UnstableApi

@SubclassOptInRequired(UnstableApi::class)
interface CoreLibraryApi {
    fun performCoreAction()
}

// Consumer Code
@OptIn(UnstableApi::class)
class CustomFeature : CoreLibraryApi {
    override fun performCoreAction() {
        println("Custom logic")
    }
}

fun main() {
    val feature: CoreLibraryApi = CustomFeature()
    feature.performCoreAction()
}
