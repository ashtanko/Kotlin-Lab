package dev.shtanko.api.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val login: String,
    val contributions: Int,
)
