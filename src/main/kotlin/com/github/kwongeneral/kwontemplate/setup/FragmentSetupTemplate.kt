package com.github.kwongeneral.kwontemplate.setup

import com.android.tools.idea.wizard.template.*
import com.github.kwongeneral.kwontemplate.extensions.defaultPackageNameParameter
import com.github.kwongeneral.kwontemplate.extensions.toSnakeCase

val fragmentSetupTemplate
    get() = template {
        name = "Kwon Fragment"
        description = "리사이클러뷰 액티비티"
        minApi = 16
        category = Category.Other // Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
            WizardUiContext.NewProject, WizardUiContext.NewModule
        )

        val packageNameParam = defaultPackageNameParameter
        val className = stringParameter {
            name = "Class Name"
            default = ""
            help = "클래스명"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val activityLayoutName = stringParameter {
            name = "Layout Name"
            default = ""
            help = "레이아웃명"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { activityToLayout(className.value.toSnakeCase()) }
        }

        widgets(
            TextFieldWidget(className),
            TextFieldWidget(activityLayoutName),
            PackageNameWidget(packageNameParam)
        )

        recipe = { data: TemplateData ->
            fragmentSetup(
                data as ModuleTemplateData,
                packageNameParam.value,
                className.value,
                activityLayoutName.value
            )
        }
    }