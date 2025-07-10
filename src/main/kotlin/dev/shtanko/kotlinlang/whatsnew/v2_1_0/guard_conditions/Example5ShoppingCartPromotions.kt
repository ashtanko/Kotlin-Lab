@file:Suppress("PackageNaming", "MagicNumber")

package dev.shtanko.kotlinlang.whatsnew.v2_1_0.guard_conditions

sealed interface CartItem {
    data class Product(val price: Double, val isOnSale: Boolean) : CartItem
    data class Coupon(val code: String, val discount: Int) : CartItem
    object Empty : CartItem
}

fun applyDiscount(item: CartItem): String = when (item) {
    is CartItem.Product if item.isOnSale && item.price > 100 -> "Apply extra 10% discount"
    is CartItem.Coupon if item.discount >= 20 -> "Apply coupon: ${item.code}"
    CartItem.Empty -> "Cart is empty"
    else -> "No discount"
}


fun main() {
    println(applyDiscount(CartItem.Product(120.0, true)))
    println(applyDiscount(CartItem.Coupon("SAVE20", 25)))
    println(applyDiscount(CartItem.Empty))
    println(applyDiscount(CartItem.Product(90.0, true)))
}
