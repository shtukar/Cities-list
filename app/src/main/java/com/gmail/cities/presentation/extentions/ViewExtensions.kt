package com.gmail.cities.presentation.extentions

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.gmail.cities.presentation.common.DebouncingOnClickListener

fun View.doOnClick(clickAction: (view: View) -> Unit) {
    this.setOnClickListener(DebouncingOnClickListener(clickAction))
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide(makeInvisible: Boolean = false) {
    this.visibility = if (makeInvisible) View.INVISIBLE else View.GONE
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun EditText.onTextChanged(onTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged.invoke(s.toString())
        }

        override fun afterTextChanged(editable: Editable?) {
        }
    })
}