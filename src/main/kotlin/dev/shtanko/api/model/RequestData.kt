package dev.shtanko.api.model

import kotlinx.serialization.Serializable

@Serializable
data class RequestData(
    val username: String,
    val password: String,
    val org: String,
)
