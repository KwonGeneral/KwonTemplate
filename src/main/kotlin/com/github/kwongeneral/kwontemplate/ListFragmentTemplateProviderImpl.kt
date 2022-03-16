package com.github.kwongeneral.kwontemplate

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.kwongeneral.kwontemplate.setup.listFragmentSetupTemplate


class ListFragmentTemplateProviderImpl : WizardTemplateProvider() {
	override fun getTemplates(): List<Template> = listOf(listFragmentSetupTemplate)
}