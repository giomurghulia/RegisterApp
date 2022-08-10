package com.example.registerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.registerapp.databinding.*
import java.lang.IllegalStateException

class MainAdapter : ListAdapter<ListItem, RecyclerView.ViewHolder>(MyDiffUtil()) {
    private var callBack: CallBack? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ListItem.ViewType.FIELD_EDIT_TEXT.ordinal -> {
                EditTextViewHolder(
                    LayoutEdittextFieldBinding.inflate(layoutInflater, parent, false)
                )
            }
            ListItem.ViewType.FIELD_RADIO_BUTTON.ordinal -> {
                RadioButtonViewHolder(
                    LayoutChooserFieldBinding.inflate(layoutInflater, parent, false)
                )
            }
            ListItem.ViewType.TOP_BORDER.ordinal -> {
                ViewHolder(
                    LayoutTopBorderBinding.inflate(layoutInflater, parent, false).root
                )
            }
            ListItem.ViewType.BOTTOM_BORDER.ordinal -> {
                ViewHolder(
                    LayoutBottomBorderBinding.inflate(layoutInflater, parent, false).root
                )
            }
            ListItem.ViewType.SPACE.ordinal -> {
                ViewHolder(
                    LayoutSpaceBinding.inflate(layoutInflater, parent, false).root
                )
            }
            else -> throw IllegalStateException()

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is EditTextViewHolder -> holder.bind(item as ListItem.FieldEditText)
            is RadioButtonViewHolder -> holder.bind(item as ListItem.FieldChooser)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.ordinal
    }

    fun setCallBack(callBack: CallBack) {
        this.callBack = callBack
    }


    inner class EditTextViewHolder(
        private val binding: LayoutEdittextFieldBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ListItem.FieldEditText) {
            binding.field1.hint = item.hint

            binding.field1.doAfterTextChanged {
                callBack?.onValueInput(item.fieldId, it?.toString().orEmpty())
            }
        }
    }


    inner class RadioButtonViewHolder(
        private val binding: LayoutChooserFieldBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ListItem.FieldChooser) {
            binding.field2.text = item.hint
        }
    }

    inner class ViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view)

    interface CallBack {
        fun onValueInput(id: Int, value: String)
    }

    companion object {
        private const val INPUT_TYPE_TEXT: String = "text"
        private const val INPUT_TYPE_NUM: String = "number"
    }
}