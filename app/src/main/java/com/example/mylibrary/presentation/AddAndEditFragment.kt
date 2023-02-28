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
import com.example.mylibrary.domain.entity.Book
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddAndEditFragment: Fragment() {

    private lateinit var viewModel: AddAndEditViewModel
    private lateinit var onEditingFinishedListener: OnEditingFinishedListener

    private var screenMode = UNKNOWN_MODE
    private var bookId = Book.UNIDENTIFIED_ID

    private lateinit var tilTitle : TextInputLayout
    private lateinit var tilAuthor : TextInputLayout
    private lateinit var etTitle : TextInputEditText
    private lateinit var etAuthor : TextInputEditText
    private lateinit var btnSave : Button
    private lateinit var btnAddImg : Button

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
        return inflater.inflate(R.layout.fragment_add_edit_book_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[AddAndEditViewModel::class.java]

        initView(view)
        addChangeErrorListener()
        launchRightMode()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.errorInputTitle.observe(viewLifecycleOwner){
            val message = if(it){
                "Can not be empty"
            }else{
                null
            }
            tilTitle.error = message
        }
        viewModel.errorInputAuthor.observe(viewLifecycleOwner){
            val message = if(it){
                "Can not be empty"
            }else{
                null
            }
            tilAuthor.error = message
        }
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
        viewModel.bookItem.observe(viewLifecycleOwner){
            etTitle.setText(it.title)
            etAuthor.setText(it.author)
        }
        btnSave.setOnClickListener {
            viewModel.editBookItem(
                etTitle.text?.toString(),
                etAuthor.text?.toString()
            )
        }
   }

    private fun launchAddMode() {
        btnSave.setOnClickListener {
            viewModel.addBookItem(
                etTitle.text?.toString(),
                etAuthor.text?.toString()
            )
        }
    }

    private fun addChangeErrorListener() {
        etTitle.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputTitle()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        etAuthor.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputAuthor()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun initView(view: View) {
        tilTitle = view.findViewById(R.id.til_title)
        tilAuthor = view.findViewById(R.id.til_author)
        etTitle = view.findViewById(R.id.et_title)
        etAuthor = view.findViewById(R.id.et_author)
        btnSave = view.findViewById(R.id.btn_save)
        btnAddImg = view.findViewById(R.id.btn_add_img)
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