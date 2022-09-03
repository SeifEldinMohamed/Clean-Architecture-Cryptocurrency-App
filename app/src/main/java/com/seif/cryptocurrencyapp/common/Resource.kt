package com.seif.cryptocurrencyapp.common

sealed class Resource<T>( // we will use this class in viewModel to parse our response from api
    val data: T? = null, // actual data from api
    val message: String? = null // message
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data, message) // if error happened than data = null

    class Loading<T>(data: T? = null)  : Resource<T>(data)
}
// (data: T? = null) : // this way we can decide if we want to display the cached data while it's loading or if we want to show an empty list