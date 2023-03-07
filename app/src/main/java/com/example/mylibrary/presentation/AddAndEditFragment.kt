package com.example.mylibrary.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mylibrary.R
import com.example.mylibrary.databinding.ActivityAddAndEditScreenBinding
import com.example.mylibrary.databinding.FragmentAddEditBookItemBinding
import com.example.mylibrary.domain.entity.Book
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddAndEditFragment: Fragment() {

    private lateinit var viewModel: AddAndEditViewModel
    private lateinit var onEditingFinishedListener: OnEditingFinishedListener

    private var screenMode = UNKNOWN_MODE
    private var bookId = Book.UNIDENTIFIED_ID


    private var _binding: FragmentAddEditBookItemBinding? = null
    private val binding: FragmentAddEditBookItemBinding
        get() = _binding ?: throw RuntimeException("FragmentAddEditBookItemBinding == null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnEditingFinishedListener){
            onEditingFinishedListener = context
        }else{
            throw RuntimeException("Should add implement OnEditingFinishedListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParam()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEditBookItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[AddAndEditViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        launchRightMode()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner){
            onEditingFinishedListener.onEditingFinished()
        }
    }

    private fun launchRightMode() {
        when(screenMode){
            MODE_ADD -> launchAddMode()
            MODE_EDIT -> launchEditMode()
        }
    }

    private fun launchEditMode() {
        viewModel.getBookItem(bookId)
        binding.btnSave.setOnClickListener {
            viewModel.editBookItem(
                binding.etTitle.text?.toString(),
                binding.etAuthor.text?.toString()
            )
        }
   }

    private fun launchAddMode() {
        binding.btnSave.setOnClickListener {
            viewModel.addBookItem(
                binding.etTitle.text?.toString(),
                binding.etAuthor.text?.toString()
            )
        }
    }


    private fun parseParam(){
        val arguments = requireArguments()
        if(!arguments.containsKey(SCREEN_MODE)){
            throw RuntimeException("Screen mode is absent")
        }
        val mode = arguments.getString(SCREEN_MODE)
        if(mode != MODE_EDIT && mode != MODE_ADD){
            throw RuntimeException("Unknown mode $mode")
        }
        screenMode = mode
        if(screenMode == MODE_EDIT){
            if(!arguments.containsKey(BOOK_ITEM_ID)){
                throw RuntimeException("Book item id is absent")
            }
            bookId = arguments.getInt(BOOK_ITEM_ID)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        private const val SCREEN_MODE = "screen_mode"
        private const val BOOK_ITEM_ID ="book_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val UNKNOWN_MODE = "unknown_mode"

        fun newInstanceAddFragment(): Fragment{
            return AddAndEditFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditFragment(bookId: Int): Fragment{
            return AddAndEditFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(BOOK_ITEM_ID, bookId)
                }
            }
        }
    }

    interface OnEditingFinishedListener{
        fun onEditingFinished()
    }
}