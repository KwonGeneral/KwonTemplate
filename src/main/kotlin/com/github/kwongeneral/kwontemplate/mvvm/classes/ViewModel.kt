package com.github.kwongeneral.kwontemplate.mvvm.classes

fun createViewModel(
    packageName: String,
    className: String
) = """
package $packageName
import androidx.lifecycle.ViewModel

class ${className}ViewModel() : ViewModel() {

}
""".trimIndent()