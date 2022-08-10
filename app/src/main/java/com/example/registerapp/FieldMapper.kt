package com.example.registerapp

object FieldMapper {

    fun convert(fields: List<List<FieldModel>>): List<ListItem> {
        return fields.flatMap {
            val list = mutableListOf<ListItem>()
            list.add(ListItem.TopBorder)

            val newFields = it.map { field ->
                if (field.field_type == "input") {
                    ListItem.FieldEditText(
                        field.field_id,
                        field.hint,
                        field.field_type,
                        field.keyboard!!,
                        field.required,
                        field.is_active,
                        field.icon
                    )
                } else {
                    ListItem.FieldChooser(
                        field.field_id,
                        field.hint,
                        field.field_type,
                        field.required,
                        field.is_active,
                        field.icon
                    )
                }

            }

            list.addAll(newFields)

            list.add(ListItem.BottomBorder)
            list.add(ListItem.Space)

            return@flatMap list
        }
    }
}