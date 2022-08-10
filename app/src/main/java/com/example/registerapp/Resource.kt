package com.example.registerapp

sealed class Resource {
    data class Success(val items: List<List<FieldModel>>) : Resource()
    data class Error(val errorMessage: String) : Resource()
    object Loading : Resource()
}