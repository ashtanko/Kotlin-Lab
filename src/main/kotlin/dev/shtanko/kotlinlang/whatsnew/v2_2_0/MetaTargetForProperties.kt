@file:Suppress("PackageNaming")

package dev.shtanko.kotlinlang.whatsnew.v2_2_0

@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.PROPERTY_GETTER,
)
@Retention(AnnotationRetention.RUNTIME)
annotation class Email

data class User(
    val username: String,

    @param:Email      // Constructor parameter
    @field:Email      // Backing field
    @get:Email        // Getter method
    @property:Email   // Kotlin property reference
    val email: String,
) {
    @field:Email
    @get:Email
    @property:Email
    val secondaryEmail: String? = null
}

fun main() {
    val emailProp = User::class.members.first { it.name == "email" }
    println("Has @Email on property: ${emailProp.annotations.any { it is Email }}")

    val constructor = User::class.constructors.first()
    val param = constructor.parameters.first { it.name == "email" }
    println("Has @Email on constructor param: ${param.annotations.any { it is Email }}")
}
