package dev.shtanko.algorithms.leetcode

class LRUCache(private val capacity: Int) {
    private var count = 0
    private val map = hashMapOf<Int, Node>()
    private var head = Node()
    private var tail = Node()

    init {
        head.next = tail
        tail.prev = head
    }

    fun get(key: Int): Int {
        val n = map[key] ?: return -1
        update(n)
        return n.value
    }

    fun put(key: Int, value: Int) {
        var n = map[key]
        if (n == null) {
            n = Node(key, value)
            map[key] = n
            add(n)
            count++
        } else {
            n.value = value
            update(n)
        }
        if (count > capacity) {
            val toDel = tail.prev
            toDel?.let { remove(it) }
            map.remove(toDel?.key)
            count--
        }
    }

    private fun update(node: Node) {
        remove(node)
        add(node)
    }

    private fun add(node: Node) {
        val after = head.next
        head.next = node
        node.prev = head
        node.next = after
        after?.prev = node
    }

    private fun remove(node: Node) {
        val before = node.prev
        val after = node.next
        before?.next = after
        after?.prev = before
    }

    private class Node(val key: Int = 0, var value: Int = 0) {
        var prev: Node? = null
        var next: Node? = null
    }
}
