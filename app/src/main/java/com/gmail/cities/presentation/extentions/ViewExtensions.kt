package com.gmail.cities.presentation.extentions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide(makeInvisible: Boolean = false) {
    this.visibility = if (makeInvisible) View.INVISIBLE else View.GONE
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