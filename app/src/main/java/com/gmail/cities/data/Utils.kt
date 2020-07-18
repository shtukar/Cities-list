package com.gmail.cities.data

import android.content.Context

fun readJSONFromAsset(context: Context, path: String): String? {
    return context.assets.open(path)
            .bufferedReader()
            .use { it.readText() }
}