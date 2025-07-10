@file:Suppress("PackageNaming", "MagicNumber")

package dev.shtanko.kotlinlang.whatsnew.v2_1_0.guard_conditions

sealed interface HttpResponse {
    data class Success(val statusCode: Int, val data: String) : HttpResponse
    data class Error(val statusCode: Int, val message: String) : HttpResponse
    object Timeout : HttpResponse
}

fun handleResponse(response: HttpResponse): String = when (response) {
    is HttpResponse.Success if response.statusCode in 200..299 -> "Success: ${response.data}"
    is HttpResponse.Error if response.statusCode == 404 -> "Error: Not Found"
    is HttpResponse.Timeout -> "Request timed out"
    else -> "Unhandled response"
}

fun main() {
    println(handleResponse(HttpResponse.Success(200, "OK")))
    println(handleResponse(HttpResponse.Error(404, "Missing")))
    println(handleResponse(HttpResponse.Timeout))
    println(handleResponse(HttpResponse.Error(500, "Server")))
}
