package com.github.kwongeneral.kwontemplate.setup

import com.android.tools.idea.wizard.template.*
import com.github.kwongeneral.kwontemplate.extensions.defaultPackageNameParameter
import com.github.kwongeneral.kwontemplate.extensions.toSnakeCase

val fragmentSetupTemplate
    get() = template {
        name = "A Kwon Fragment (MVVM)"
        description = "Custom Fragment Template"
        minApi = 16
        category = Category.Fragment // Check other categories
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

        val fragmentLayoutName = stringParameter {
            name = "Layout Name"
            default = ""
            help = "레이아웃명"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { fragmentToLayout(className.value.toSnakeCase()) }
        }

        widgets(
            TextFieldWidget(className),
            TextFieldWidget(fragmentLayoutName),
            PackageNameWidget(packageNameParam)
        )

        recipe = { data: TemplateData ->
            fragmentSetup(
                data as ModuleTemplateData,
                packageNameParam.value,
                className.value,
                fragmentLayoutName.value
            )
        }
    }