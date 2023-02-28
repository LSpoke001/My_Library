package com.example.mylibrary.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mylibrary.R

class TestFragment: Fragment() {

    private lateinit var tvTitle: TextView
    private lateinit var tvAuthor: TextView

    private var title = TEXT_VIEW_DEFAULT
    private var author = TEXT_VIEW_DEFAULT

    private val viewModelFactory by lazy{
        TestViewModelFactory(title, author)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[TestViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParam()
    }

    private fun parseParam() {
        val args = requireArguments()
        title = args.getString(TITLE).toString()
        author = args.getString(AUTHOR).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.test_vm_factory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)

        viewModel.getTextViewItems()

        viewModel.titleLD.observe(viewLifecycleOwner){
            tvTitle.text = it
        }
        viewModel.authorLD.observe(viewLifecycleOwner){
            tvAuthor.text = it
        }

    }

    private fun initView(view: View) {
        tvTitle = view.findViewById(R.id.title_tv)
        tvAuthor = view.findViewById(R.id.author_tv)
    }

    companion object{
        private const val TEXT_VIEW_DEFAULT = "Empty"

        private const val TITLE = "title"
        private const val AUTHOR = "author"

        fun newInstanceTestFactory(first: String, second: String): TestFragment{
            return TestFragment().apply {
                arguments = Bundle().apply {
                    putString(TITLE, first)
                    putString(AUTHOR, second)
                }
            }
        }
    }
}