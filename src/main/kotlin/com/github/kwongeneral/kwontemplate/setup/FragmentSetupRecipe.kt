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
    activityLayoutName: String
) {
    val (projectData, _, _, manifestOut) = moduleData
    val project = projectInstance ?: return

    addAllKotlinDependencies(moduleData)

    val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots
    val virtSrc = virtualFiles.firstOrNull { it.path.contains("app/src/main/java") }?:return
    val virtRes = virtualFiles.firstOrNull { it.path.contains("app/src/main/res") }?:return
    val directorySrc = PsiManager.getInstance(project).findDirectory(virtSrc)?:return
    val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)?:return

    val activityClass = "${className}Activity".capitalize()
    val adapterClass = "${className}RecyclerAdatper".capitalize()
    val viewHolderClass = "${className}ItemViewHolder".capitalize()
    val viewModelClass = "${className}ViewModel".capitalize()

    mergeXml(
        manifestTemplateXml(packageName, "${className}Activity"),
        manifestOut.resolve("AndroidManifest.xml")
    )

    val packageNameSplit = packageName.split(".")
    val originPackageName = "${packageNameSplit[0]}.${packageNameSplit[1]}.${packageNameSplit[2]}"

    createFragment(packageName, className, activityLayoutName, originPackageName)
        .save(directorySrc, "$packageName.view", "$activityClass.kt")

    createRecyclerAdapter(packageName, className)
        .save(directorySrc, "$packageName.adapter", "$adapterClass.kt")

    createViewHolder(packageName, className)
        .save(directorySrc, "$packageName.viewHolder", "$viewHolderClass.kt")

    createViewModel(packageName, className)
        .save(directorySrc, "$packageName.view.model", "$viewModelClass.kt")

    createRecyclerActivityLayout(packageName, className)
        .save(directoryRes, "layout", "${activityLayoutName}.xml")

    createViewHolderLayout()
        .save(directoryRes, "layout", "item_${className.toSnakeCase()}.xml")
}