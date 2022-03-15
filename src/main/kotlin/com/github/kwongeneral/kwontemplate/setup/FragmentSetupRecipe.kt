package com.github.kwongeneral.kwontemplate.setup

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.*
import com.github.kwongeneral.kwontemplate.extensions.save
import com.github.kwongeneral.kwontemplate.extensions.toSnakeCase
import com.github.kwongeneral.kwontemplate.listeners.MyProjectManagerListener.Companion.projectInstance
import com.intellij.openapi.roots.*
import com.intellij.psi.*
import com.github.kwongeneral.kwontemplate.mvvm.classes.*
import com.github.kwongeneral.kwontemplate.mvvm.layout.*


fun RecipeExecutor.fragmentSetup(
    moduleData: ModuleTemplateData,
    packageName: String,
    className: String,
    fragmentLayoutName: String
) {
    val (projectData, _, _, manifestOut) = moduleData
    val project = projectInstance ?: return

    addAllKotlinDependencies(moduleData)

    val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots
    val virtSrc = virtualFiles.firstOrNull { it.path.contains("app/src/main/java") }?:return
    val virtRes = virtualFiles.firstOrNull { it.path.contains("app/src/main/res") }?:return
    val directorySrc = PsiManager.getInstance(project).findDirectory(virtSrc)?:return
    val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)?:return

    val fragmentClass = "${className}Fragment".capitalize()
    val adapterClass = "${className}Adapter".capitalize()
    val dataClass = "${className}Data".capitalize()
    val viewModelClass = "${className}ViewModel".capitalize()
    val itemName = "${className.toLowerCase()}_item"
    val adapterLayoutName = "adapter_${className.toSnakeCase()}"
    val recyclerName = "recycler_${className.toLowerCase()}"
    val classNameCapitalize = className.capitalize()

    val packageNameSplit = packageName.split(".")
    val originPackageName = "${packageNameSplit[0]}.${packageNameSplit[1]}.${packageNameSplit[2]}"

    createFragment("$packageName.view.fragment", classNameCapitalize, fragmentLayoutName, viewModelClass, itemName, recyclerName, adapterClass, originPackageName)
        .save(directorySrc, "$packageName.view.fragment", "$fragmentClass.kt")

    createAdapter("$packageName.view.adapter", classNameCapitalize, dataClass, adapterLayoutName, originPackageName)
        .save(directorySrc, "$packageName.view.adapter", "$adapterClass.kt")

    createViewModel("$packageName.view.model", classNameCapitalize, itemName, dataClass, originPackageName)
        .save(directorySrc, "$packageName.view.model", "$viewModelClass.kt")

    createData("$packageName.data", classNameCapitalize)
        .save(directorySrc, "$packageName.data", "$dataClass.kt")

    createFragmentLayout(classNameCapitalize, recyclerName)
        .save(directoryRes, "layout", "${fragmentLayoutName}.xml")

    createAdapterLayout()
        .save(directoryRes, "layout", "$adapterLayoutName.xml")
}