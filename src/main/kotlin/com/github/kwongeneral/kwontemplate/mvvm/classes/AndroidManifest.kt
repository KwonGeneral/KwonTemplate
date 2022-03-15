package com.github.kwongeneral.kwontemplate.mvvm.classes

fun manifestTemplateXml(packageName: String, activityClassName: String) = """
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools">

    <application>
        <activity 
            android:name="$packageName.$activityClassName"
            android:exported="false"/>
    </application>
</manifest>
        """