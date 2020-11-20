package dev.shtanko.oop

open class ForwardingSet<E>(set: MutableSet<E>) : MutableSet<E> {

    private val s: MutableSet<E> = set

    override fun add(element: E) = s.add(element)
    override fun addAll(elements: Collection<E>) = s.addAll(elements)
    override fun clear() = s.clear()
    override fun iterator(): MutableIterator<E> = s.iterator()
    override fun remove(element: E): Boolean = s.remove(element)
    override fun removeAll(elements: Collection<E>) = s.removeAll(elements)
    override fun retainAll(elements: Collection<E>) = s.retainAll(elements)
    override val size: Int
        get() = s.size

    override fun contains(element: E) = s.contains(element)
    override fun containsAll(elements: Collection<E>) = s.containsAll(elements)
    override fun isEmpty() = s.isEmpty()
}

class CompositionInstrumentedHashSet<E>(set: MutableSet<E>) : ForwardingSet<E>(set) {

    private var addCount = 0

    override fun add(element: E): Boolean {
        addCount++
        return super.add(element)
    }

    override fun addAll(elements: Collection<E>): Boolean {
        addCount += elements.size
        return super.addAll(elements)
    }

    fun getAddCount(): Int = addCount
}