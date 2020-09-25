package com.example.treerecyclerview

sealed class AppFailure(
    open val message: String?
)

sealed class ApiErrors(message: String?) : AppFailure(message) {

    data class NetworkConnection(override val message: String? = null) : ApiErrors(message)
    data class ServerError(override val message: String? = null) : ApiErrors(message)
    data class Unauthorized(override val message: String? = null) : ApiErrors(message)

}

sealed class ValidationErrors(message: String?) : AppFailure(message) {
    data class Validation(override val message: String? = null) : ValidationErrors(message)
    data class Mapping(override val message: String? = null) : ValidationErrors(message)
}

sealed class GeneralErrors(message: String?) : AppFailure(message) {
    data class General(override val message: String? = null) : GeneralErrors(message)
}


