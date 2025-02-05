/*
 * Copyright 2022 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.TreeMap

/**
 * 692. Top K Frequent Words
 */
fun interface TopKFrequent {
    operator fun invoke(words: Array<String>, k: Int): List<String>
}

private class Comparator1 : Comparator<Map.Entry<String, Int>> {
    override fun compare(o1: Map.Entry<String, Int>, o2: Map.Entry<String, Int>): Int {
        val word1: String = o1.key
        val freq1: Int = o1.value
        val word2: String = o2.key
        val freq2: Int = o2.value
        return if (freq1 != freq2) {
            freq1 - freq2
        } else {
            word2.compareTo(word1)
        }
    }
}

class Comparator2 : Comparator<Map.Entry<String, Int>> {
    override fun compare(o1: Map.Entry<String, Int>, o2: Map.Entry<String, Int>): Int {
        val word1 = o1.key
        val freq1 = o1.value
        val word2 = o2.key
        val freq2 = o2.value
        return if (freq1 != freq2) {
            freq2 - freq1
        } else {
            word1.compareTo(word2)
        }
    }
}

class TopKFrequentSorting : TopKFrequent {
    override operator fun invoke(words: Array<String>, k: Int): List<String> {
        val map: MutableMap<String, Int> = HashMap()
        for (word in words) {
            map[word] = map.getOrDefault(word, 0) + 1
        }
        val l: MutableList<Map.Entry<String, Int>> = LinkedList()
        for (e in map.entries) {
            l.add(e)
        }
        l.sortWith(Comparator2())

        val ans: MutableList<String> = LinkedList()
        for (i in 0 until k) {
            ans.add(l[i].key)
        }
        return ans
    }
}

class TopKFrequentMinHeap : TopKFrequent {
    override operator fun invoke(words: Array<String>, k: Int): List<String> {
        val map: MutableMap<String, Int> = HashMap()
        for (word in words) {
            map[word] = map.getOrDefault(word, 0) + 1
        }
        val comparator = Comparator1()
        val pq: PriorityQueue<Map.Entry<String, Int>> = PriorityQueue(comparator)
        for (e in map.entries) {
            // If minHeap's size is smaller than K, we just add the entry
            if (pq.size < k) {
                pq.offer(e)
            } else {
                if (pq.peek() != null && comparator.compare(e, pq.peek()) > 0) {
                    pq.poll()
                    pq.offer(e)
                }
            }
        }
        val ans: MutableList<String> = LinkedList()
        for (i in 0 until k) {
            ans.add(0, pq.poll().key)
        }
        return ans
    }
}

class TopKFrequentMap : TopKFrequent {
    override operator fun invoke(words: Array<String>, k: Int): List<String> {
        val result: MutableList<String> = LinkedList()
        val map: MutableMap<String, Int> = HashMap()
        for (i in words.indices) {
            if (map.containsKey(words[i])) map[words[i]] = map[words[i]]!! + 1 else map[words[i]] = 1
        }

        val pq: PriorityQueue<Map.Entry<String, Int>> = PriorityQueue { a, b ->
            if (a.value == b.value) b.key.compareTo(a.key) else a.value - b.value
        }

        for (entry in map.entries) {
            pq.offer(entry)
            if (pq.size > k) {
                pq.poll()
            }
        }

        while (pq.isNotEmpty()) result.add(0, pq.poll().key)

        return result
    }
}

class TopKFrequentTrie : TopKFrequent {

    override operator fun invoke(words: Array<String>, k: Int): List<String> {
        val map: MutableMap<String, Int> = HashMap()
        for (word in words) {
            map[word] = map.getOrDefault(word, 0) + 1
        }

        val buckets = arrayOfNulls<Trie>(words.size)
        for ((word, freq) in map) {
            // for each word, add it into trie at its bucket
            if (buckets[freq] == null) {
                buckets[freq] = Trie()
            }
            buckets[freq]?.addWord(word)
        }

        val ans: MutableList<String> = LinkedList()

        for (i in buckets.indices.reversed()) {
            // for trie in each bucket, get all the words with same frequency in lexicographic order.
            // Compare with k and get the result
            if (buckets[i] != null) {
                val l: MutableList<String> = LinkedList()
                buckets[i]?.getWords(buckets[i]?.root, l)
                if (l.size < k) {
                    ans.addAll(l)
                    k - l.size
                } else {
                    for (j in 0 until k) {
                        ans.add(l[j])
                    }
                    break
                }
            }
        }
        return ans
    }

    class TrieNode {
        var children = arrayOfNulls<TrieNode>(ALPHABET_LETTERS_COUNT)
        var word: String? = null
    }

    class Trie {
        var root = TrieNode()
        fun addWord(word: String) {
            var cur: TrieNode? = root
            for (c in word.toCharArray()) {
                if (cur!!.children[c.code - 'a'.code] == null) {
                    cur.children[c.code - 'a'.code] = TrieNode()
                }
                cur = cur.children[c.code - 'a'.code]
            }
            cur?.word = word
        }

        fun getWords(node: TrieNode?, ans: MutableList<String>) {
            if (node == null) {
                return
            }
            if (node.word != null) {
                ans.add(node.word ?: return)
            }
            for (i in 0 until ALPHABET_LETTERS_COUNT) {
                if (node.children[i] != null) {
                    getWords(node.children[i], ans)
                }
            }
        }
    }
}

class TopKFrequentBucketSort : TopKFrequent {
    override operator fun invoke(words: Array<String>, k: Int): List<String> {
        val map: MutableMap<String, Int> = HashMap()
        for (word in words) {
            map[word] = map.getOrDefault(word, 0) + 1
        }
        val buckets: Array<TreeMap<String, Int>?> = arrayOfNulls(words.size)
        for ((word, freq) in map) {
            if (buckets[freq] == null) {
                buckets[freq] = TreeMap { a: String, b: String? ->
                    a.compareTo(b!!)
                }
            }
            buckets[freq]!![word] = freq
        }

        val ans: MutableList<String> = LinkedList()
        for (i in buckets.indices.reversed()) {
            if (buckets[i] != null) {
                var k0 = k
                val temp = buckets[i] ?: return emptyList()
                if (temp.size < k0) {
                    k0 -= temp.size
                    while (temp.isNotEmpty()) {
                        ans.add(temp.pollFirstEntry().key)
                    }
                } else {
                    while (k0 > 0) {
                        ans.add(temp.pollFirstEntry().key)
                        k0--
                    }
                    break
                }
            }
        }
        return ans
    }
}
