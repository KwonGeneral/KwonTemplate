package com.github.kwongeneral.kwontemplate.extensions

import com.android.tools.idea.wizard.template.*
import com.intellij.psi.*
import org.jetbrains.kotlin.idea.*

fun String.toSnakeCase() = replace(humps, "_").toLowerCase()
private val humps = "(?<=.)(?=\\p{Upper})".toRegex()

val defaultPackageNameParameter
    get() = stringParameter {
        name = "Package name"
        visible = { !isNewModule }
        default = "com.kwon.template" //기본 패키지명
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }

fun String.save(srcDir: PsiDirectory, subDirPath: String, fileName: String) {
    try {
        val destDir = subDirPath.split(".").toDir(srcDir)
        val psiFile = PsiFileFactory
            .getInstance(srcDir.project)
            .createFileFromText(fileName, KotlinLanguage.INSTANCE, this)
        destDir.add(psiFile)
    } catch (exc: Exception) {
        exc.printStackTrace()
    }
}

fun List<String>.toDir(srcDir: PsiDirectory): PsiDirectory {
    var result = srcDir
    forEach {
        result = result.findSubdirectory(it) ?: result.createSubdirectory(it)
    }
    return result
}