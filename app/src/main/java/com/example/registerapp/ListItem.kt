package com.example.registerapp

sealed class ListItem(val viewType: ViewType) {
    enum class ViewType {
        FIELD_EDIT_TEXT,
        FIELD_RADIO_BUTTON,
        SPACE,
        TOP_BORDER,
        BOTTOM_BORDER
    }

    data class FieldEditText(
        val fieldId: Int,
        val hint: String,
        val fieldType: String,
        val keyboard: String,
        val required: Boolean,
        val isActive: Boolean,
        val icon: String,
    ) : ListItem(ViewType.FIELD_EDIT_TEXT)

    data class FieldChooser(
        val fieldId: Int,
        val hint: String,
        val fieldType: String,
        val required: Boolean,
        val isActive: Boolean,
        val icon: String
    ) : ListItem(ViewType.FIELD_RADIO_BUTTON)

    object Space : ListItem(ViewType.SPACE)
    object TopBorder : ListItem(ViewType.TOP_BORDER)
    object BottomBorder : ListItem(ViewType.BOTTOM_BORDER)
}