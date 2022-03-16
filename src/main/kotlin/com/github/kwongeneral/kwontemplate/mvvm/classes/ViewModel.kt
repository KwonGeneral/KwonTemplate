package com.github.kwongeneral.kwontemplate.mvvm.classes

fun createViewModel(
    packageName: String,
    path: String,
    className: String,
    itemName: String,
    dataName: String,
    originPackageName: String
) = """
package $packageName$path

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import $packageName.data.$dataName

class ${className}ViewModel(context: Context): ViewModel() {
    val $itemName = MutableLiveData<ArrayList<$dataName>>()

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

    fun update() {

    }

    private fun insert(data: $dataName) {
        update()
    }

    private fun modify(data: $dataName) {
        update()
    }

    private fun delete(data: $dataName) {
        update()
    }
}
""".trimIndent()