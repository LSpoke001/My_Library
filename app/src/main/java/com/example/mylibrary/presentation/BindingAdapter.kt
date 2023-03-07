package com.example.mylibrary.presentation

import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorInput")
fun bindingErrorInputTitle(textInputLayout: TextInputLayout, errorTitle: Boolean){
    val message = if(errorTitle){
        "Can not be empty"
    }else{
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("bookIcon")
fun bindingBookIcon(imageView: ImageView, resource: Int){
    imageView.setImageResource(resource)
}

@BindingAdapter("errorListenerTitle")
fun bindingAddChangeErrorListenerTitle(editText: TextInputEditText, viewModel: AddAndEditViewModel){
    editText.addTextChangedListener ( object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            viewModel.resetErrorInputTitle()
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    })
}

@BindingAdapter("errorListenerAuthor")
fun bindingAddChangeErrorListenerAuthor(editText: TextInputEditText, viewModel: AddAndEditViewModel){
    editText.addTextChangedListener ( object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            viewModel.resetErrorInputAuthor()
        }

        override fun afterTextChanged(p0: Editable?) {
        }

    })
}