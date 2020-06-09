package dev.shtanko.algorithms.leetcode

import java.util.*

fun IntArray.trapRainWater(): Int {
    if (this.isEmpty()) return 0

    var low = 0
    var high = size - 1
    var maxLeft = 0
    var maxRight = 0
    var ans = 0

    while (low <= high) {
        if (this[low] <= this[high]) {
            if (this[low] >= maxLeft) maxLeft = this[low]
            else ans += maxLeft - this[low]
            low++
        } else {
            if (this[high] >= maxRight) maxRight = this[high]
            ans += maxRight - this[high]
            high--
        }
    }

    return ans
}

fun IntArray.trapRainWaterUsingStack(): Int {
    var ans = 0
    var current = 0
    val stack = Stack<Int>()
    while (current < this.size) {
        while (stack.isNotEmpty() && this[current] > this[stack.peek()]) {
            val top = stack.peek()
            stack.pop()
            if (stack.isEmpty()) break
            val distance = current - stack.peek() - 1
            val boundedHeight = this[current].coerceAtMost(this[stack.peek()] - this[top])
            ans += distance * boundedHeight
        }
        stack.push(current++)
    }
    return ans
}