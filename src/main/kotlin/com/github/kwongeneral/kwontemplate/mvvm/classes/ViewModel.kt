package com.github.kwongeneral.kwontemplate.mvvm.classes

fun createViewModel(
    packageName: String,
    path: String,
    className: String
) = """
package $packageName$path

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel

class ${className}ViewModel(context: Context): ViewModel() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: ${className}ViewModel? = null
        fun getInstance(context: Context): ${className}ViewModel {
            instance?.let {
                return it
            }
            instance = ${className}ViewModel(context)
            return instance!!
        }

        fun releaseInstance() {
            instance = null
        }
    }
}
""".trimIndent()