package com.github.kwongeneral.kwontemplate.services

import com.intellij.openapi.project.Project
import com.github.kwongeneral.kwontemplate.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
