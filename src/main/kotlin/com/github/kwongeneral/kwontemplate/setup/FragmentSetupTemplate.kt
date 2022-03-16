package com.github.kwongeneral.kwontemplate.setup

import com.android.tools.idea.wizard.template.*
import com.github.kwongeneral.kwontemplate.extensions.defaultPackageNameParameter
import java.util.*

val fragmentSetupTemplate
    get() = template {
        name = "A Fragment (MVVM)"
        description = "Fragment Template (Fragment, ViewModel, Layout)"
        minApi = 21
        category = Category.Fragment
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
            WizardUiContext.NewProject, WizardUiContext.NewModule
        )

        val packageNameParam = defaultPackageNameParameter
        val className = stringParameter {
            name = "Class Name"
            default = ""
            help = "Class Name"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val fragmentLayoutName = stringParameter {
            name = "Layout Name"
            default = ""
            help = "Layout Name"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = {
                val tempName = className.value.lowercase(Locale.getDefault()).replace("fragment", "")
                "fragment_$tempName"
            }
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