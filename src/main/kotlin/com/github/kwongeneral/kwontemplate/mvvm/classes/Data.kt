package com.github.kwongeneral.kwontemplate.mvvm.classes

fun createData(
    packageName: String,
    path: String,
    className: String
) = """
package $packageName$path

data class ${className}Data(
    var type: Long = 0,
    var sub_type: Long = 0,
) {
}
""".trimIndent()