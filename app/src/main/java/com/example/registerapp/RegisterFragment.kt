package com.example.registerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.registerapp.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val adapter = MainAdapter()

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.mainRecycleView.layoutManager =
            GridLayoutManager(context, 1)
        binding.mainRecycleView.adapter = adapter

        viewModel.getFields()

        adapter.setCallBack(object : MainAdapter.CallBack {
            override fun onValueInput(id: Int, value: String) {
                viewModel.onValueInput(id, value)
            }
        })

        binding.registerButton.setOnClickListener {
            viewModel.onRegisterClick()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.errorFlow.collect {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }

//        adapter.submitList(FieldMapper.convert(FIELDS))

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fields.collect {

                    binding.loaderProgressBar.visibility = View.GONE
                    when (it) {
                        is Resource.Success -> {
                            adapter.submitList(FieldMapper.convert(it.items))
                            Toast.makeText(context, "Success Add Items", Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Error -> {
                            Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Loading -> {
                            binding.loaderProgressBar.visibility = View.VISIBLE
                        }
                    }

                }
            }
        }
    }

}

val ALL_FIELDS = listOf(
    ListItem.TopBorder,
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldChooser(
        2,
        "Birthday",
        "chooser",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.BottomBorder,
    ListItem.Space, ListItem.TopBorder,
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldChooser(
        2,
        "Birthday",
        "chooser",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.BottomBorder,
    ListItem.Space, ListItem.TopBorder,
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldChooser(
        2,
        "Birthday",
        "chooser",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.BottomBorder,
    ListItem.Space, ListItem.TopBorder,
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldChooser(
        2,
        "Birthday",
        "chooser",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.BottomBorder,
    ListItem.Space, ListItem.TopBorder,
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldChooser(
        2,
        "Birthday",
        "chooser",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.BottomBorder,
    ListItem.Space, ListItem.TopBorder,
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldChooser(
        2,
        "Birthday",
        "chooser",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.BottomBorder,
    ListItem.Space, ListItem.TopBorder,
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldChooser(
        2,
        "Birthday",
        "chooser",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.BottomBorder,
    ListItem.Space, ListItem.TopBorder,
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldChooser(
        2,
        "Birthday",
        "chooser",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.BottomBorder,
    ListItem.Space, ListItem.TopBorder,
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldEditText(
        1,
        "UserName2",
        "input",
        "text",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.FieldChooser(
        2,
        "Birthday",
        "chooser",
        false,
        true,
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
    ),
    ListItem.BottomBorder,
    ListItem.Space
)
val FIELDS = listOf(
    listOf(
        FieldModel(
            1,
            "UserName2",
            "input",
            "text",
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        ),
        FieldModel(
            2,
            "Birthday",
            "chooser",
            null,
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        ), FieldModel(
            3,
            "Birthday",
            "chooser",
            null,
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        )
    ),
    listOf(
        FieldModel(
            122,
            "UserName2",
            "input",
            "text",
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        ),
        FieldModel(
            23,
            "Birthday",
            "chooser",
            null,
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        ), FieldModel(
            34,
            "Birthday",
            "chooser",
            null,
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        )
    ), listOf(
        FieldModel(
            1,
            "UserName2",
            "input",
            "text",
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        ),
        FieldModel(
            2,
            "Birthday",
            "chooser",
            null,
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        ), FieldModel(
            3,
            "Birthday",
            "chooser",
            null,
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        )
    ),
    listOf(
        FieldModel(
            122,
            "UserName2",
            "input",
            "text",
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        ),
        FieldModel(
            23,
            "Birthday",
            "chooser",
            null,
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        ), FieldModel(
            34,
            "Birthday",
            "chooser",
            null,
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        )
    ), listOf(
        FieldModel(
            1,
            "UserName2",
            "input",
            "text",
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        ),
        FieldModel(
            2,
            "Birthday",
            "chooser",
            null,
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        ), FieldModel(
            3,
            "Birthday",
            "chooser",
            null,
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        )
    ),
    listOf(
        FieldModel(
            122,
            "UserName2",
            "input",
            "text",
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        ),
        FieldModel(
            23,
            "Birthday",
            "chooser",
            null,
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        ), FieldModel(
            34,
            "Birthday",
            "chooser",
            null,
            false,
            true,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/2048px-2021_Facebook_icon.svg.png"
        )
    )
)
