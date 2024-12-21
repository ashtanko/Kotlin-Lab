/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.patterns.structural.composite.example1

// Component: Abstract base class for both files and directories
abstract class FileSystemComponent(val name: String) {
    abstract fun display(indent: String)
}

// Leaf: Represents a file
class File(name: String) : FileSystemComponent(name) {
    override fun display(indent: String) {
        println("$indent File: $name")
    }
}

// Composite: Represents a directory that can contain files or subdirectories
class Directory(name: String) : FileSystemComponent(name) {
    private val children = mutableListOf<FileSystemComponent>()

    fun add(component: FileSystemComponent) {
        children.add(component)
    }

    override fun display(indent: String) {
        println("$indent Directory: $name")
        for (child in children) {
            child.display("$indent  ")
        }
    }
}

fun main() {
    val root = Directory("Root")
    val subDir1 = Directory("Subdirectory 1")
    val subDir2 = Directory("Subdirectory 2")

    val file1 = File("File 1")
    val file2 = File("File 2")

    root.add(subDir1)
    root.add(subDir2)
    root.add(file1)
    subDir1.add(file2)

    root.display("")
}
