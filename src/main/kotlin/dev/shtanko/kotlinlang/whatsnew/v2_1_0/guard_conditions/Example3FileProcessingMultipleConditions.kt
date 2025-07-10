@file:Suppress("PackageNaming", "MagicNumber")

package dev.shtanko.kotlinlang.whatsnew.v2_1_0.guard_conditions

sealed interface File {
    data class Image(val extension: String, val sizeMB: Double) : File
    data class Video(val duration: Int, val format: String) : File
    object Unknown : File
}

fun process(file: File): String = when (file) {
    is File.Image if file.sizeMB < 5 -> "Processing small image"
    is File.Video if file.duration > 120 -> "Compressing long video"
    is File.Unknown -> "Unknown file type"
    else -> "General processing"
}

fun main() {
    println(process(File.Image("jpg", 2.5)))
    println(process(File.Video(150, "mp4")))
    println(process(File.Unknown))
    println(process(File.Image("png", 12.0)))
}
