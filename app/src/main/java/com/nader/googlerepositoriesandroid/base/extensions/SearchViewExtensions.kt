package com.nader.googlerepositoriesandroid.base.extensions

import androidx.appcompat.widget.SearchView


fun SearchView.onTextChanged(listener: (String?) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean = true

        override fun onQueryTextChange(newText: String?): Boolean {
            listener.invoke(newText)
            return true
        }

    })
}