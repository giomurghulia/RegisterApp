package com.example.registerapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _fields = MutableStateFlow<Resource>(Resource.Loading)
    val fields get() = _fields.asStateFlow()

    private val _errorFlow = MutableSharedFlow<String>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val errorFlow get() = _errorFlow.asSharedFlow()


    private var items: List<List<FieldModel>> = emptyList()
    private val fieldMap = mutableMapOf<Int, String>()

    fun getFields() {
        viewModelScope.launch {
            val respons = RetrofitClient.fieldsService().getFields()

            if (respons.isSuccessful) {
                val body = respons.body()
                _fields.value = Resource.Success(body ?: emptyList())

                items = body ?: emptyList()
            } else {
                val errorBody = respons.errorBody()
                _fields.value = Resource.Error(errorBody?.toString() ?: "")
            }
        }
    }

    fun onValueInput(id: Int, value: String) {
        fieldMap[id] = value
    }

    fun onRegisterClick() {
        val invalidFields = mutableListOf<FieldModel>()
        items.flatten()?.forEach { field ->
            if (field.field_type != "chooser" && field.required && fieldMap[field.field_id].isNullOrBlank()) {
                invalidFields.add(field)
            }
        }

        _errorFlow.tryEmit(invalidFields.map { "${it.hint}\n" }.toString())
    }

}