package com.github.kwongeneral.kwontemplate.mvvm.layout

fun createRecyclerActivityLayout(
    packageName: String,
    className: String
) = """
    <?xml version="1.0" encoding="utf-8"?>
    <layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:bind="http://schemas.android.com/tools"
        xmlns:tools="http://schemas.android.com/tools"
        tools:context="$packageName.${className}Activity">

        <data>

            <variable
                name="viewModel"
                type="$packageName.${className}ViewModel" />

        </data>

        <androidx.constraintlayout.widget.ConstraintLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent">

        </androidx.constraintlayout.widget.ConstraintLayout>

    </layout>
""".trimIndent()